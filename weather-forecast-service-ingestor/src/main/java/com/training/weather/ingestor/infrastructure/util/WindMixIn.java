package com.training.weather.ingestor.infrastructure.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface WindMixIn {
  @JsonProperty("deg")
  Double getDegree();
}
