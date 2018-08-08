package com.training.weather.ingestor.infrastructure.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.training.weather.ingestor.infrastructure.owm.OpenWeatherMapForecast;

import java.util.List;

public interface OpenWeatherMapResponseMixIn {
  @JsonProperty("cod")
  String getCode();

  @JsonProperty("list")
  List<OpenWeatherMapForecast> getForecasts();
}
