package com.training.weather.ingestor.core;

import com.training.weather.ingestor.core.model.Coordinates;
import com.training.weather.ingestor.core.model.WeatherForecast;

import java.util.List;

public interface WeatherDataSource {
  List<WeatherForecast> getForecasts(Coordinates coordinates);
}
