package com.training.weather.ingestor.infrastructure.jackson.owm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.training.weather.ingestor.infrastructure.model.owm.Forecast;

import java.util.List;

public interface OpenWeatherMapResponseMixIn {
  @JsonProperty("cod")
  String getCode();

  @JsonProperty("list")
  List<Forecast> getForecasts();
}
