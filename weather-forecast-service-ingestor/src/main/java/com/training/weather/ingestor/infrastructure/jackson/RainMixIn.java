package com.training.weather.ingestor.infrastructure.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface RainMixIn {
  @JsonProperty("3h")
  double getVolume();
}
