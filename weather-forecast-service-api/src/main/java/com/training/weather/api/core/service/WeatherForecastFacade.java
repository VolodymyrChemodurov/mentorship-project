package com.training.weather.api.core.service;

import com.training.weather.core.model.WeatherForecast;

import java.time.LocalDateTime;

public interface WeatherForecastFacade {
  WeatherForecast getWeatherForecastByCoordinates(double longitude,
                                                  double latitude,
                                                  LocalDateTime dateTime);
}

