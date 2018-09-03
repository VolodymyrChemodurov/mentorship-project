package com.training.weather.ingestor.core.service;

import com.training.weather.ingestor.core.repository.WeatherForecastDataSource;

import java.util.Collection;

public class WeatherForecastCachingFacade {

  private final WeatherForecastDataSource weatherForecastDataSource;

  private final WeatherForecastProcessor weatherForecastProcessor;

  private final IngestionSource ingestionSource;

  /**
   * Constructor.
   *
   * @param weatherForecastDataSource      weatherDataSource
   * @param weatherForecastProcessor WeatherForecastProcessorImpl
   * @param ingestionSource         IngestionSource
   */
  public WeatherForecastCachingFacade(
          WeatherForecastDataSource weatherForecastDataSource,
          WeatherForecastProcessor weatherForecastProcessor,
          IngestionSource ingestionSource) {
    this.weatherForecastDataSource = weatherForecastDataSource;
    this.weatherForecastProcessor = weatherForecastProcessor;
    this.ingestionSource = ingestionSource;
  }

  /**
   * Method for retrieving weather forecasts from OpenWeather API and process them.
   */
  public void refresh() {
    ingestionSource.get().stream()
        .map(weatherForecastDataSource::getForecasts)
        .flatMap(Collection::stream)
        .forEach(weatherForecastProcessor::process);
  }
}
