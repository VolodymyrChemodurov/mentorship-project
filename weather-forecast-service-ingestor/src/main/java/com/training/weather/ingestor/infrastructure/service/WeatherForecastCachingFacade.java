package com.training.weather.ingestor.infrastructure.service;

import com.training.weather.ingestor.core.model.City;
import com.training.weather.ingestor.core.model.Forecast;
import com.training.weather.ingestor.core.model.OpenWeatherMapResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherForecastCachingFacade implements WeatherCachingFacade {

  private final WeatherDataSource weatherDataSource;

  private final WeatherForecastService weatherForecastService;

  private final List<City> cities;

  /**
   * Constructor.
   *
   * @param weatherDataSource      weatherDataSource
   * @param weatherForecastService WeatherForecastRedisService
   * @param cities                 List&ltCity&gt
   */
  public WeatherForecastCachingFacade(
          WeatherDataSource weatherDataSource,
          WeatherForecastService weatherForecastService,
          List<City> cities) {
    this.weatherDataSource = weatherDataSource;
    this.weatherForecastService = weatherForecastService;
    this.cities = cities;
  }

  /**
   * Method for retrieving weather forecasts from OpenWeather API and storing it to Redis.
   */
  public void cache() {
    cities.forEach((city) -> {
      OpenWeatherMapResponse response = weatherDataSource.getForecasts(city.getCoordinates());

      List<Forecast> forecasts = response.getForecasts();

      forecasts.forEach((forecast) -> weatherForecastService.save(forecast, city));
    });
  }
}
