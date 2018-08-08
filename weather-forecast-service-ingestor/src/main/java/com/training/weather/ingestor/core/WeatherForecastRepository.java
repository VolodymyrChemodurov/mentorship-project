package com.training.weather.ingestor.core;

import com.training.weather.ingestor.core.model.WeatherForecast;
import com.training.weather.ingestor.core.model.WeatherForecastKey;

public interface WeatherForecastRepository {
  String save(WeatherForecastKey weatherForecastKey, WeatherForecast weatherForecast);
}
