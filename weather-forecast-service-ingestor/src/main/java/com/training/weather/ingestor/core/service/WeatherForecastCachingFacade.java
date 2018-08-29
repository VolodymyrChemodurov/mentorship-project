package com.training.weather.ingestor.core.service;

import com.training.weather.ingestor.core.model.City;
import com.training.weather.ingestor.core.repository.CityRepository;
import com.training.weather.ingestor.core.repository.WeatherForecastDataSource;

import java.util.Iterator;

public class WeatherForecastCachingFacade {

  private static final int MAX_REQUESTS_PER_MINUTE = 60;

  private final WeatherForecastDataSource weatherForecastDataSource;

  private final WeatherForecastProcessor weatherForecastProcessor;

  private final CityRepository cityRepository;

  private Iterator<City> cityIterator;

  /**
   * Constructor.
   *
   * @param weatherForecastDataSource weatherDataSource
   * @param weatherForecastProcessor  WeatherForecastProcessorImpl
   * @param cityRepository            CityResourceRepository
   */
  public WeatherForecastCachingFacade(
          WeatherForecastDataSource weatherForecastDataSource,
          WeatherForecastProcessor weatherForecastProcessor,
          CityRepository cityRepository) {
    this.weatherForecastDataSource = weatherForecastDataSource;
    this.weatherForecastProcessor = weatherForecastProcessor;
    this.cityRepository = cityRepository;
    this.cityIterator = cityRepository.getAll().iterator();
  }

  /**
   * Method for retrieving weather forecasts from OpenWeather API and process them.
   */
  public void refresh() {

    for (int i = 0; i < MAX_REQUESTS_PER_MINUTE; i++) {
      if (!cityIterator.hasNext()) {
        resetIterator();
      }

      weatherForecastDataSource.getForecasts(cityIterator.next())
              .forEach(weatherForecastProcessor::process);
    }
  }

  private void resetIterator() {
    this.cityIterator = cityRepository.getAll().iterator();
  }
}
