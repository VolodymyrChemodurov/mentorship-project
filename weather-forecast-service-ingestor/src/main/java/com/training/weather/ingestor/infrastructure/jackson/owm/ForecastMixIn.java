package com.training.weather.ingestor.infrastructure.jackson.owm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.training.weather.ingestor.infrastructure.model.owm.MainParameters;

public interface ForecastMixIn {
  @JsonProperty("dt")
  Integer getTimestamp();

  @JsonProperty("main")
  MainParameters getMainParameters();

  @JsonProperty("dt_txt")
  String getDate();
}
