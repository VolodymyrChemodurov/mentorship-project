package com.training.weather.ingestor.core.service;

import com.training.weather.ingestor.core.repository.CityRepository;

public class WeatherForecastForecastCachingFacadeImpl implements WeatherForecastCachingFacade {

  private final WeatherDataSource weatherDataSource;

  private final WeatherForecastProcessor weatherForecastProcessor;

  private final CityRepository cityRepository;

  /**
   * Constructor.
   *
   * @param weatherDataSource      weatherDataSource
   * @param weatherForecastProcessor WeatherForecastProcessorImpl
   * @param cityRepository         CityResourceRepository
   */
  public WeatherForecastForecastCachingFacadeImpl(
          WeatherDataSource weatherDataSource,
          WeatherForecastProcessor weatherForecastProcessor,
          CityRepository cityRepository) {
    this.weatherDataSource = weatherDataSource;
    this.weatherForecastProcessor = weatherForecastProcessor;
    this.cityRepository = cityRepository;
  }

  /**
   * Method for retrieving weather forecasts from OpenWeather API and storing it to Redis.
   */
  public void refresh() {
    cityRepository.getAll().forEach(city -> weatherDataSource.getForecasts(city)
            .forEach(forecast -> weatherForecastProcessor.save(forecast, city)));
  }
}
