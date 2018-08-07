package com.training.weather.ingestor.infrastructure.util.owm.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface CloudsMixIn {
  @JsonProperty("all")
  Integer getVolume();
}
