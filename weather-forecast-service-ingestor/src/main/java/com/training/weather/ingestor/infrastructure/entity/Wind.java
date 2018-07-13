
package com.training.weather.ingestor.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "speed",
        "deg"})
public class Wind {

  @JsonProperty("speed")
  private Double speed;
  @JsonProperty("deg")
  private Double deg;

  /**
   * Creates instance of {@link Wind}.
   */
  public Wind() {
    //Default Constructor.
  }

  public Wind(Double speed, Double deg) {
    this.speed = speed;
    this.deg = deg;
  }

  public Double getSpeed() {
    return speed;
  }

  public void setSpeed(Double speed) {
    this.speed = speed;
  }

  public Double getDeg() {
    return deg;
  }

  public void setDeg(Double deg) {
    this.deg = deg;
  }
}
