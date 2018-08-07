package com.training.weather.ingestor.infrastructure.util.owm.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.training.weather.ingestor.core.model.owm.Forecast;

import java.util.List;

public interface OpenWeatherMapResponseMixIn {
  @JsonProperty("cod")
  String getCode();

  @JsonProperty("list")
  List<Forecast> getForecasts();
}
