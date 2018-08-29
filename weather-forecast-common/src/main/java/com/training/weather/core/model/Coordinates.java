
package com.training.weather.core.model;

public class Coordinates {

  private double latitude;

  private double longitude;

  /**
   * Creates instance of {@link Coordinates}.
   */
  public Coordinates() {
    //Default Constructor.
  }

  /**
   * Constructor.
   *
   * @param latitude  double
   * @param longitude double
   */
  public Coordinates(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  @Override
  public String toString() {
    return "Coordinates{"
            + "latitude=" + latitude
            + ", longitude=" + longitude
            + '}';
  }
}
