package com.training.weather.ingestor.infrastructure.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface WindMixIn {
  @JsonProperty("deg")
  Double getDegree();
}
