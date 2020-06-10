package org.epam.vladkabat;

import ch.hsr.geohash.GeoHash;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Produced;
import org.epam.vladkabat.entities.Hotel;
import org.epam.vladkabat.entities.Result;
import org.epam.vladkabat.entities.Weather;
import org.epam.vladkabat.serdes.JsonDeserializer;
import org.epam.vladkabat.serdes.JsonSerializer;

public class Application {

  public static void main(String[] args) throws SQLException, ClassNotFoundException {
    Properties config = getKafkaProperties();
    StreamsBuilder builder = new StreamsBuilder();

    Serde<Weather> weatherSerde = getJsonWeatherSerDes();
    Serde<Result> resultSerde = getJsonResultSerDes();

    List<Hotel> hotels = getHotels();

    builder
        .stream("input-topic", Consumed.with(Serdes.String(), weatherSerde))
        .flatMapValues(weather -> {
          String geoHashWeather = geoConvert(weather.getLat(), weather.getLng());
          Hotel hotel = getResultHotel(geoHashWeather, 5, hotels);
          if (hotel == null) {
            hotel = getResultHotel(geoHashWeather, 4, hotels);
            if (hotel == null) {
              hotel = getResultHotel(geoHashWeather, 3, hotels);
            }
          }
          if (hotel != null) {
            return Collections
                .singletonList(new Result(weather.getDay(), geoHashWeather, hotel.getName()));
          }
          return null;
        })
        .to("output-topic", Produced.with(Serdes.String(), resultSerde));

    KafkaStreams streams = new KafkaStreams(builder.build(), config);
    streams.start();

    Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
  }

  private static Serde<Weather> getJsonWeatherSerDes() {
    JsonSerializer<Weather> jsonWeatherSerializer = new JsonSerializer<>();
    JsonDeserializer<Weather> jsonWeatherDeserializer = new JsonDeserializer<>(Weather.class);
    return Serdes.serdeFrom(jsonWeatherSerializer, jsonWeatherDeserializer);
  }

  private static Serde<Result> getJsonResultSerDes() {
    JsonSerializer<Result> jsonResultSerializer = new JsonSerializer<>();
    JsonDeserializer<Result> jsonResultDeserializer = new JsonDeserializer<>(Result.class);
    return Serdes.serdeFrom(jsonResultSerializer, jsonResultDeserializer);
  }

  private static Properties getKafkaProperties() {
    Properties config = new Properties();
    config.put(StreamsConfig.APPLICATION_ID_CONFIG, "task2-kafka");
    config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "sandbox-hdp.hortonworks.com:6667");
    config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    config.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 100);
    config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);
    return config;
  }

  private static Hotel getResultHotel(String geoHashWeather, int index, List<Hotel> hotels) {
    for (Hotel hotel : hotels) {
      if (hotel.getGeoHash().substring(0, index).equals(geoHashWeather.substring(0, index))) {
        return hotel;
      }
    }
    return null;
  }

  private static List<Hotel> getHotels() throws ClassNotFoundException, SQLException {
    Class.forName("org.apache.hive.jdbc.HiveDriver");
    Connection con = DriverManager
        .getConnection("jdbc:hive2://sandbox-hdp.hortonworks.com:10000/default", "hive", "");
    Statement stmt = con.createStatement();
    ResultSet res = stmt.executeQuery("select * from hotels");

    List<Hotel> listHotels = new ArrayList<>();
    while (res.next()) {
      Hotel hotel = new Hotel(res.getLong(1), res.getString(2),
          res.getString(3), res.getString(4), res.getString(5),
          res.getDouble(6), res.getDouble(7));
      hotel.setGeoHash(geoConvert(hotel.getLatitude(), hotel.getLongitude()));
      listHotels.add(hotel);
    }
    return listHotels;
  }

  private static String geoConvert(double lat, double lon) {
    GeoHash geoHash = GeoHash.withCharacterPrecision(lat, lon, 5);
    return geoHash.toBase32();
  }
}
