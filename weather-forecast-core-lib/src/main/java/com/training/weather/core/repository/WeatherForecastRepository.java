package com.training.weather.core.repository;

import com.training.weather.core.model.WeatherForecast;

public interface WeatherForecastRepository {
  void save(WeatherForecast weatherForecast);
}
