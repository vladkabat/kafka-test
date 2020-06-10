package org.epam.vladkabat.entities;

public class Result {

  private int day;
  private String geoHashWeather;
  private String hotelName;

  public Result(int day, String geoHashWeather, String hotelName) {
    this.day = day;
    this.geoHashWeather = geoHashWeather;
    this.hotelName = hotelName;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public String getGeoHashWeather() {
    return geoHashWeather;
  }

  public void setGeoHashWeather(String geoHashWeather) {
    this.geoHashWeather = geoHashWeather;
  }

  public String getHotelName() {
    return hotelName;
  }

  public void setHotelName(String hotelName) {
    this.hotelName = hotelName;
  }
}
