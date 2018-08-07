package com.training.weather.ingestor.core.service;

import com.training.weather.ingestor.core.entity.WeatherForecastWrapper;
import com.training.weather.ingestor.core.model.owm.City;

import java.util.List;

public interface WeatherDataSource {
  List<WeatherForecastWrapper> getForecasts(City city);
}
