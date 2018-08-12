package com.training.weather.ingestor.core.service;

import com.training.weather.ingestor.core.model.Coordinates;
import com.training.weather.ingestor.infrastructure.entity.redis.OpenWeatherMapResponse;

public interface WeatherDataSource {
  OpenWeatherMapResponse getForecasts(Coordinates coordinates);
}
