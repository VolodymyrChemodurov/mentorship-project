
package com.training.weather.ingestor.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "coord",
        "country"})
public class City {

  @JsonProperty("id")
  private Integer id;
  @JsonProperty("name")
  private String name;
  @JsonProperty("coord")
  private Coordinates coordinates;
  @JsonProperty("country")
  private String country;

  /**
   * Creates instance of {@link City}.
   */
  public City() {
    //Default Constructor.
  }

  /**
   * Creates instance of {@link City}.
   * @param id Integer.
   * @param name String.
   * @param coordinates Coordinates.
   * @param country String.
   */
  public City(Integer id, String name, Coordinates coordinates, String country) {
    this.id = id;
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
