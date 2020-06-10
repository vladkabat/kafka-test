package org.epam.vladkabat.entities;

public class Weather {

  private double lng;
  private double lat;
  private double avg_tmpr_f;
  private double avg_tmpr_c;
  private String wthr_date;
  private int year;
  private int month;
  private int day;
  private String geoHash;

  public Weather(double lng, double lat, double avg_tmpr_f, double avg_tmpr_c,
      String wthr_date, int year, int month, int day) {
    this.lng = lng;
    this.lat = lat;
    this.avg_tmpr_f = avg_tmpr_f;
    this.avg_tmpr_c = avg_tmpr_c;
    this.wthr_date = wthr_date;
    this.year = year;
    this.month = month;
    this.day = day;
  }

  @Override
  public String toString() {
    return "Weather{" +
        "lng=" + lng +
        ", lat=" + lat +
        ", avg_tmpr_f=" + avg_tmpr_f +
        ", avg_tmpr_c=" + avg_tmpr_c +
        ", wthr_date='" + wthr_date + '\'' +
        ", year=" + year +
        ", month=" + month +
        ", day=" + day +
        ", geoHash='" + geoHash + '\'' +
        '}';
  }

  public void setLng(double lng) {
    this.lng = lng;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public void setAvg_tmpr_f(double avg_tmpr_f) {
    this.avg_tmpr_f = avg_tmpr_f;
  }

  public void setAvg_tmpr_c(double avg_tmpr_c) {
    this.avg_tmpr_c = avg_tmpr_c;
  }

  public void setWthr_date(String wthr_date) {
    this.wthr_date = wthr_date;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public void setGeoHash(String geoHash) {
    this.geoHash = geoHash;
  }

  public double getLng() {
    return lng;
  }

  public double getLat() {
    return lat;
  }

  public double getAvg_tmpr_f() {
    return avg_tmpr_f;
  }

  public double getAvg_tmpr_c() {
    return avg_tmpr_c;
  }

  public String getWthr_date() {
    return wthr_date;
  }

  public int getYear() {
    return year;
  }

  public int getMonth() {
    return month;
  }

  public int getDay() {
    return day;
  }

  public String getGeoHash() {
    return geoHash;
  }
}
