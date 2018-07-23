package com.training.weather.ingestor.infrastructure.repository;

import com.training.weather.ingestor.infrastructure.entity.redis.WeatherForecast;
import com.training.weather.ingestor.infrastructure.entity.redis.WeatherForecastKey;

public interface WeatherForecastRepository {
  String save(WeatherForecastKey weatherForecastKey, WeatherForecast weatherForecast);
}
