package com.training.weather.ingestor.infrastructure.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.training.weather.ingestor.core.model.Coordinates;

public interface CityMixIn {
  @JsonProperty("coord")
  Coordinates getCoordinates();
}
