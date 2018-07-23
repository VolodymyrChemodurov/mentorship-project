package com.training.weather.ingestor.infrastructure.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.training.weather.ingestor.core.model.MainParameters;

public interface ForecastMixIn {
  @JsonProperty("dt")
  Integer getTimestamp();

  @JsonProperty("main")
  MainParameters getMainParameters();

  @JsonProperty("dt_txt")
  String getDate();
}