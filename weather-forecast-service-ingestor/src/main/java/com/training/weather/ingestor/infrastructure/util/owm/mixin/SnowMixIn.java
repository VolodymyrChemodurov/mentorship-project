package com.training.weather.ingestor.infrastructure.util.owm.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface SnowMixIn {
  @JsonProperty("3h")
  String getVolume();
}
