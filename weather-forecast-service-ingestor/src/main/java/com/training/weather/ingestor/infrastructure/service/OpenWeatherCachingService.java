package com.training.weather.ingestor.infrastructure.service;

import com.training.weather.ingestor.infrastructure.entity.City;
import com.training.weather.ingestor.infrastructure.entity.Forecast;
import com.training.weather.ingestor.infrastructure.entity.OpenWeatherForecastResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenWeatherCachingService {

  private final OpenWeatherService openWeatherService;

  private final WeatherForecastRedisService weatherForecastRedisService;

  private final List<City> cities;

  /**
   * Constructor.
   * @param openWeatherService OpenWeatherService
   * @param weatherForecastRedisService WeatherForecastRedisService
   * @param cities List&ltCity&gt
   */
  public OpenWeatherCachingService(
          OpenWeatherService openWeatherService,
          WeatherForecastRedisService weatherForecastRedisService,
          List<City> cities) {
    this.openWeatherService = openWeatherService;
    this.weatherForecastRedisService = weatherForecastRedisService;
    this.cities = cities;
  }

  /**
   * Method for retrieving weather forecasts from OpenWeather API and storing it to Redis.
   */
  public void cacheWeatherForecasts() {
    for (City city : cities) {

      OpenWeatherForecastResponse response = openWeatherService
              .getForecastByCoordinates(city.getCoordinates());

      List<Forecast> forecasts = response.getForecasts();

      forecasts.forEach((forecast) -> {
        weatherForecastRedisService.save(forecast, city);
      });
    }
  }
}
