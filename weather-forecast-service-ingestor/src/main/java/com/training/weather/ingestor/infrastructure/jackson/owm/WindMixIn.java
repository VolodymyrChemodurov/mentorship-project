package com.training.weather.ingestor.infrastructure.jackson.owm;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface WindMixIn {
  @JsonProperty("deg")
  Double getDegree();
}
