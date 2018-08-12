package com.training.weather.ingestor.core.repository;

import com.training.weather.ingestor.core.entity.WeatherForecast;
import com.training.weather.ingestor.core.entity.WeatherForecastKey;

public interface WeatherForecastRepository {
  String save(WeatherForecastKey weatherForecastKey, WeatherForecast weatherForecast);
}
