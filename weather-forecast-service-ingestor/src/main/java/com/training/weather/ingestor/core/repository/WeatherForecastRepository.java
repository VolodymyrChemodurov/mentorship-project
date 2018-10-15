package com.training.weather.ingestor.core.repository;

import com.training.weather.core.model.WeatherForecast;

public interface WeatherForecastRepository {
  void save(WeatherForecast weatherForecast);
}
