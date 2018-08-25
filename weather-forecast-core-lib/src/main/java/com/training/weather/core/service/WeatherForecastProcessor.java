package com.training.weather.core.service;

import com.training.weather.core.model.WeatherForecast;
import com.training.weather.core.repository.WeatherForecastRepository;

public class WeatherForecastProcessor {

  private final WeatherForecastRepository weatherForecastRepository;

  public WeatherForecastProcessor(
      WeatherForecastRepository weatherForecastRepository) {
    this.weatherForecastRepository = weatherForecastRepository;
  }

  public void process(WeatherForecast forecast) {
    weatherForecastRepository.save(forecast);
  }
}
