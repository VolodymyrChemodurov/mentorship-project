
package com.training.weather.ingestor.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "3h"})
public class Rain {
  @JsonProperty("3h")
  private double volume;

  public Rain() {
  }

  public Rain(double volume) {
    this.volume = volume;
  }

  public double getVolume() {
    return volume;
  }

  public void setVolume(double volume) {
    this.volume = volume;
  }
}
