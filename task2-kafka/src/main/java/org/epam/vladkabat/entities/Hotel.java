package org.epam.vladkabat.entities;

public class Hotel {

  private long Id;
  private String Name;
  private String Country;
  private String City;
  private String Address;
  private double Latitude;
  private double Longitude;
  private String geoHash;

  public Hotel(long id, String name, String country, String city, String address, double latitude,
      double longitude) {
    Id = id;
    Name = name;
    Country = country;
    City = city;
    Address = address;
    Latitude = latitude;
    Longitude = longitude;
  }

  @Override
  public String toString() {
    return "Hotel{" +
        "Id=" + Id +
        ", Name='" + Name + '\'' +
        ", Country='" + Country + '\'' +
        ", City='" + City + '\'' +
        ", Address='" + Address + '\'' +
        ", Latitude=" + Latitude +
        ", Longitude=" + Longitude +
        ", geoHash='" + geoHash + '\'' +
        '}';
  }

  public long getId() {
    return Id;
  }

  public void setId(long id) {
    Id = id;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public String getCountry() {
    return Country;
  }

  public void setCountry(String country) {
    Country = country;
  }

  public String getCity() {
    return City;
  }

  public void setCity(String city) {
    City = city;
  }

  public String getAddress() {
    return Address;
  }

  public void setAddress(String address) {
    Address = address;
  }

  public double getLatitude() {
    return Latitude;
  }

  public void setLatitude(double latitude) {
    Latitude = latitude;
  }

  public double getLongitude() {
    return Longitude;
  }

  public void setLongitude(double longitude) {
    Longitude = longitude;
  }

  public String getGeoHash() {
    return geoHash;
  }

  public void setGeoHash(String geoHash) {
    this.geoHash = geoHash;
  }
}
