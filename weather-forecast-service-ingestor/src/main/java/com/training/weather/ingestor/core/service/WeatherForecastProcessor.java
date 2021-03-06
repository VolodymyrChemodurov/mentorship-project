package com.training.weather.ingestor.core.service;

import com.training.weather.ingestor.core.model.WeatherForecast;
import com.training.weather.ingestor.core.repository.WeatherForecastRepository;

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
