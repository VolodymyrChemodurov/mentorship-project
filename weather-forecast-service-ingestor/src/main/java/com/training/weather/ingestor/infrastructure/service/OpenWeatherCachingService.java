package com.training.weather.ingestor.infrastructure.service;

import com.training.weather.ingestor.infrastructure.entity.City;
import com.training.weather.ingestor.infrastructure.entity.Forecast;
import com.training.weather.ingestor.infrastructure.entity.OpenWeatherForecastResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenWeatherCachingService {

  @Autowired
  private OpenWeatherService openWeatherService;

  @Autowired
  private WeatherForecastRedisService weatherForecastRedisService;

  @Autowired
  private List<City> cities;

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
