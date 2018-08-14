package com.training.weather.ingestor.infrastructure.jackson.owm;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface CloudsMixIn {
  @JsonProperty("all")
  Integer getVolume();
}
