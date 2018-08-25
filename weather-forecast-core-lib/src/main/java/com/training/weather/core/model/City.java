
package com.training.weather.core.model;

public class City {

  private String name;
  private Coordinates coordinates;
  private String country;

  /**
   * Constructor for {@link City}.
   */
  public City() {
    //DefaultConstructor.
  }

  /**
   * Constructor for {@link City}.
   * @param name String
   * @param coordinates Coordinates
   * @param country String
   */
  public City(String name, Coordinates coordinates, String country) {
    this.name = name;
    this.coordinates = coordinates;
    this.country = country;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return "City{"
            + "name='" + name + '\''
            + ", coordinates=" + coordinates
            + ", country='" + country + '\''
            + '}';
  }
}
