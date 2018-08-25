package com.training.weather.core.repository;

import com.training.weather.core.model.City;
import com.training.weather.core.model.WeatherForecast;

import java.util.List;

public interface WeatherForecastDataSource {
  List<WeatherForecast> getForecasts(City city);
}
