package com.training.weather.ingestor.core;

import java.util.Collection;

public class WeatherForecastCachingFacade {

  private final WeatherDataSource weatherDataSource;
  private final WeatherForecastProcessor weatherForecastProcessor;
  private final CityRepository cityRepository;

  /**
   * Initialization of WeatherForecastCachingFacade.
   */
  public WeatherForecastCachingFacade(
          WeatherDataSource weatherDataSource,
          WeatherForecastProcessor weatherForecastProcessor,
          CityRepository cityRepository) {
    this.weatherDataSource = weatherDataSource;
    this.weatherForecastProcessor = weatherForecastProcessor;
    this.cityRepository = cityRepository;
  }

  /**
   * Updating cities forecasts cache.
   */
  public void cache() {
    cityRepository.getAll().stream()
            .map(city -> weatherDataSource.getForecasts(city.getCoordinates()))
            .flatMap(Collection::stream)
            .forEach(weatherForecastProcessor::process);
  }
}
