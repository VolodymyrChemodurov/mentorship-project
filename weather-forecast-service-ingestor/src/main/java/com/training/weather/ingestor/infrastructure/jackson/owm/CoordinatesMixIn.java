package com.training.weather.ingestor.infrastructure.jackson.owm;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface CoordinatesMixIn {
  @JsonProperty("lat")
  double getLatitude();

  @JsonProperty("lon")
  double getLongitude();
}
