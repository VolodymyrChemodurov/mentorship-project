package com.training.weather.ingestor.infrastructure.util.owm.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.training.weather.ingestor.core.model.owm.Coordinates;

public interface CityMixIn {
  @JsonProperty("coord")
  Coordinates getCoordinates();
}