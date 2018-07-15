
package com.training.weather.ingestor.core.model;

public class City {

  private Integer id;
  private String name;
  private Coordinates coordinates;
  private String country;

  /**
   * Constructor.
   */
  public City() {
    //DefaultConstructor.
  }

  /**
   * Constructor for {@link City}
   * @param name String
   * @param coordinates Coordinates
   * @param country String
   */
  public City(String name, Coordinates coordinates, String country) {
    this.name = name;
    this.coordinates = coordinates;
    this.country = country;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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
}
