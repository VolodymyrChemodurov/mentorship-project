package com.training.weather.api.core.service;

import com.training.weather.core.model.Coordinates;
import com.training.weather.core.model.WeatherForecast;

import java.time.LocalDateTime;
import java.util.Optional;

public interface WeatherForecastService {
  Optional<WeatherForecast> getWeatherForecastByCoordinates(Coordinates coordinates,
                                                            LocalDateTime dateTime);
}
