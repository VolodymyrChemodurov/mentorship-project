
package com.training.weather.ingestor.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "all"})
public class Clouds {

  @JsonProperty("all")
  private Integer all;

  /**
   * Creates instance of {@link Clouds}.
   */
  public Clouds() {
    //Default Constructor.
  }

  public Clouds(Integer all) {
    this.all = all;
  }

  public Integer getAll() {
    return all;
  }

  public void setAll(Integer all) {
    this.all = all;
  }
}
