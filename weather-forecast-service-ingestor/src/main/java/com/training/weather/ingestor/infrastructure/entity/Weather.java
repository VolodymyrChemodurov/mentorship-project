
package com.training.weather.ingestor.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "main",
        "description",
        "icon"})
public class Weather {

  @JsonProperty("id")
  private Integer id;
  @JsonProperty("main")
  private String main;
  @JsonProperty("description")
  private String description;
  @JsonProperty("icon")
  private String icon;

  /**
   * Creates instance of {@link Weather}.
   */
  public Weather() {
    //Default Constructor.
  }

  /**
   * Constructor.
   * @param id Integer
   * @param main String
   * @param description String
   * @param icon String
   */
  public Weather(Integer id, String main, String description, String icon) {
    this.id = id;
    this.main = main;
    this.description = description;
    this.icon = icon;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getMain() {
    return main;
  }

  public void setMain(String main) {
    this.main = main;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }
}
