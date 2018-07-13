package com.training.weather.ingestor.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "3h"})
public class Snow {
  @JsonProperty("3h")
  private String volume;

  public Snow() {
  }

  public Snow(String volume) {
    this.volume = volume;
  }

  public String getVolume() {
    return volume;
  }

  public void setVolume(String volume) {
    this.volume = volume;
  }
}
