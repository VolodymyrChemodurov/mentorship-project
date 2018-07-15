package com.training.weather.ingestor.infrastructure.service;

import com.training.weather.ingestor.core.model.Coordinates;
import com.training.weather.ingestor.core.model.OpenWeatherMapResponse;

public interface WeatherDataSource {
  OpenWeatherMapResponse getForecasts(Coordinates coordinates);
}
