package com.training.weather.ingestor.core.repository;

import com.training.weather.core.model.WeatherForecast;
import com.training.weather.ingestor.core.model.City;

import java.util.List;

public interface WeatherForecastDataSource {
  List<WeatherForecast> getForecasts(City city);
}
