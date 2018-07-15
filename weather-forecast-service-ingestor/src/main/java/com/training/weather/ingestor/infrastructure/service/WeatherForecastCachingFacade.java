package com.training.weather.ingestor.infrastructure.service;

import com.training.weather.ingestor.core.model.City;
import com.training.weather.ingestor.core.model.Forecast;
import com.training.weather.ingestor.core.model.OpenWeatherMapResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherForecastCachingFacade {

  private final OpenWeatherMapDataSource openWeatherMapDataSource;

  private final WeatherForecastRedisService weatherForecastRedisService;

  private final List<City> cities;

  /**
   * Constructor.
   *
   * @param openWeatherMapDataSource    OpenWeatherMapDataSource
   * @param weatherForecastRedisService WeatherForecastRedisService
   * @param cities                      List&ltCity&gt
   */
  public WeatherForecastCachingFacade(
          OpenWeatherMapDataSource openWeatherMapDataSource,
          WeatherForecastRedisService weatherForecastRedisService,
          List<City> cities) {
    this.openWeatherMapDataSource = openWeatherMapDataSource;
    this.weatherForecastRedisService = weatherForecastRedisService;
    this.cities = cities;
  }

  /**
   * Method for retrieving weather forecasts from OpenWeather API and storing it to Redis.
   */
  public void cacheWeatherForecasts() {
    cities.forEach((city) -> {
      OpenWeatherMapResponse response = openWeatherMapDataSource.getForecasts(city.getCoordinates());

      List<Forecast> forecasts = response.getForecasts();

      forecasts.forEach((forecast) -> weatherForecastRedisService.save(forecast, city));
    });
  }
}
