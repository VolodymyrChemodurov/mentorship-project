package com.training.weather.ingestor.infrastructure.service;

import com.training.weather.ingestor.core.service.WeatherCachingFacade;
import com.training.weather.ingestor.core.service.WeatherDataSource;
import com.training.weather.ingestor.core.service.WeatherForecastService;
import com.training.weather.ingestor.infrastructure.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WeatherForecastCachingFacade implements WeatherCachingFacade {

  private final static Logger LOG = LoggerFactory.getLogger(WeatherForecastCachingFacade.class);

  private final WeatherDataSource weatherDataSource;

  private final WeatherForecastService weatherForecastService;

  private final CityRepository cityRepository;

  /**
   * Constructor.
   *
   * @param weatherDataSource      weatherDataSource
   * @param weatherForecastService WeatherForecastRedisService
   * @param cityRepository         CityRepository
   */
  public WeatherForecastCachingFacade(
          WeatherDataSource weatherDataSource,
          WeatherForecastService weatherForecastService,
          CityRepository cityRepository) {
    this.weatherDataSource = weatherDataSource;
    this.weatherForecastService = weatherForecastService;
    this.cityRepository = cityRepository;
  }

  /**
   * Method for retrieving weather forecasts from OpenWeather API and storing it to Redis.
   */
  public void cache() {
    cityRepository.get().forEach(city -> weatherDataSource.getForecasts(city.getCoordinates()).
            getForecasts().forEach(forecast -> weatherForecastService.save(forecast, city)));
  }
}
