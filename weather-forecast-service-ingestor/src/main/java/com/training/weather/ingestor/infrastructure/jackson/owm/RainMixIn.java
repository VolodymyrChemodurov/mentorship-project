package com.training.weather.ingestor.infrastructure.jackson.owm;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface RainMixIn {
  @JsonProperty("3h")
  double getVolume();
}
