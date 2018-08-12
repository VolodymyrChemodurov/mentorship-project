package com.training.weather.ingestor.core.service;

import com.training.weather.ingestor.core.model.City;
import com.training.weather.ingestor.core.model.Forecast;

public interface WeatherForecastService {
  void save(Forecast forecast, City city);
}
