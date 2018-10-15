package com.training.weather.api.core.repository;

import com.training.weather.core.model.Coordinates;
import com.training.weather.core.model.WeatherForecast;

import java.time.LocalDateTime;
import java.util.Set;

public interface WeatherForecastRepository {
  Set<WeatherForecast> getForecastsByCoordinates(Coordinates coordinates,
                                                 LocalDateTime localDateTime);
}
