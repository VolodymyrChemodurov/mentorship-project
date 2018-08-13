package com.training.weather.ingestor.core.repository;

import com.training.weather.ingestor.core.model.City;
import com.training.weather.ingestor.core.model.WeatherForecast;

import java.util.List;

public interface WeatherForecastDataSource {
  List<WeatherForecast> getForecasts(City city);
}
