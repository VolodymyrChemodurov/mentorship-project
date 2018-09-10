package com.training.weather.ingestor.core.service;

import com.training.weather.ingestor.core.repository.WeatherForecastDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * Deprecated because it is synchronized.
 */
public class SyncWeatherForecastCachingFacade implements WeatherForecastCachingFacade {

  private static final Logger LOG = LoggerFactory.getLogger(SyncWeatherForecastCachingFacade.class);

  private final WeatherForecastDataSource weatherForecastDataSource;

  private final WeatherForecastProcessor weatherForecastProcessor;

  private final IngestionSource ingestionSource;

  /**
   * Constructor.
   *
   * @param weatherForecastDataSource weatherDataSource
   * @param weatherForecastProcessor  WeatherForecastProcessorImpl
   * @param ingestionSource           IngestionSource
   */
  public SyncWeatherForecastCachingFacade(
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
