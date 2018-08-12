package com.training.weather.ingestor.core.service;

import com.training.weather.ingestor.core.model.WeatherForecastWrapper;
import com.training.weather.ingestor.core.model.owm.City;

public interface WeatherForecastProcessor<W extends WeatherForecastWrapper> {
  void save(W wrapper, City city);
}
