package com.training.weather.ingestor.core.service;

import com.training.weather.ingestor.core.repository.CityRepository;
import com.training.weather.ingestor.core.repository.WeatherForecastDataSource;

import java.util.Collection;

public class WeatherForecastCachingFacade {

  private final WeatherForecastDataSource weatherForecastDataSource;

  private final WeatherForecastProcessor weatherForecastProcessor;

  private final CityRepository cityRepository;

  /**
   * Constructor.
   *
   * @param weatherForecastDataSource      weatherDataSource
   * @param weatherForecastProcessor WeatherForecastProcessorImpl
   * @param cityRepository         CityResourceRepository
   */
  public WeatherForecastCachingFacade(
          WeatherForecastDataSource weatherForecastDataSource,
          WeatherForecastProcessor weatherForecastProcessor,
          CityRepository cityRepository) {
    this.weatherForecastDataSource = weatherForecastDataSource;
    this.weatherForecastProcessor = weatherForecastProcessor;
    this.cityRepository = cityRepository;
  }

  /**
   * Method for retrieving weather forecasts from OpenWeather API and process them.
   */
  public void refresh() {
    cityRepository.getAll().stream()
        .map(weatherForecastDataSource::getForecasts)
        .flatMap(Collection::stream)
        .forEach(weatherForecastProcessor::process);
  }
}
