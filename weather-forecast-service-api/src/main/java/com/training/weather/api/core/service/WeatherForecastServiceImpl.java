package com.training.weather.api.core.service;

import com.training.weather.api.core.repository.WeatherForecastRepository;
import com.training.weather.core.model.Coordinates;
import com.training.weather.core.model.WeatherForecast;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

public class WeatherForecastServiceImpl implements WeatherForecastService {
  private final WeatherForecastRepository weatherForecastRepository;

  public WeatherForecastServiceImpl(WeatherForecastRepository weatherForecastRepository) {
    this.weatherForecastRepository = weatherForecastRepository;
  }

  /**
   * Method for retrieving the most recent forecast.
   * @param coordinates {@link Coordinates}.
   * @param dateTime {@link LocalDateTime}.
   * @return {@link Optional&ltWeatherForecast&gt}.
   */
  public Optional<WeatherForecast> getWeatherForecastByCoordinates(Coordinates coordinates,
                                                                   LocalDateTime dateTime) {
    return weatherForecastRepository.getForecastsByCoordinates(coordinates, dateTime)
            .stream()
            .max(Comparator.comparing(WeatherForecast::getCreated));
  }
}
