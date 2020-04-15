package org.apache.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Consumer {

    public static final String TOPIC_NAME = "test";
    public static final String KAFKA_CONFIG_NAME = "/config-consumer.properties";

    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        props.load(Consumer.class.getResourceAsStream(KAFKA_CONFIG_NAME));
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(TOPIC_NAME));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                List<Long> list = Stream.iterate(new long[]{0, 1}, t -> new long[]{t[1], t[0] + t[1]})
                        .limit(Long.parseLong(record.value()))
                        .map(n -> n[0])
                        .collect(Collectors.toList());
                System.out.println(list);
            }
        }
    }
}
