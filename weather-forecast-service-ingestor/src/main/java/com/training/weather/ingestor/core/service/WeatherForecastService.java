package com.training.weather.ingestor.core.service;

import com.training.weather.ingestor.core.entity.WeatherForecastWrapper;
import com.training.weather.ingestor.core.model.owm.City;

public interface WeatherForecastService<W extends WeatherForecastWrapper> {
  void save(W wrapper, City city);
}
