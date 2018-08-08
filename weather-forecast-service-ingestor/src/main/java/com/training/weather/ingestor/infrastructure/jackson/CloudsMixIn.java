package com.training.weather.ingestor.infrastructure.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface CloudsMixIn {
  @JsonProperty("all")
  Integer getVolume();
}
