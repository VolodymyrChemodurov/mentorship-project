package com.training.weather.ingestor.infrastructure.service;

import com.training.weather.ingestor.core.model.City;
import com.training.weather.ingestor.core.model.Coordinates;
import com.training.weather.ingestor.core.model.Forecast;
import com.training.weather.ingestor.infrastructure.entity.redis.WeatherForecast;
import com.training.weather.ingestor.infrastructure.entity.redis.WeatherForecastKey;
import com.training.weather.ingestor.infrastructure.repository.WeatherForecastRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class WeatherForecastRedisService implements WeatherForecastService {
  private static final Logger LOG = Logger.getLogger(WeatherForecastRedisService.class);

  private final WeatherForecastRepository weatherForecastRepository;

  public WeatherForecastRedisService(
          WeatherForecastRepository weatherForecastRepository) {
    this.weatherForecastRepository = weatherForecastRepository;
  }

  /**
   * Method for storing weather forecast to Redis.
   */

  public void save(Forecast forecast, City city) {
    Coordinates coordinates = city.getCoordinates();

    WeatherForecastKey weatherForecastKey = new WeatherForecastKey();
    weatherForecastKey.setCoordinates(coordinates);
    weatherForecastKey.setTimestamp(forecast.getTimestamp());

    WeatherForecast weatherForecast = new WeatherForecast();
    weatherForecast.setForecast(forecast);

    weatherForecastRepository.save(weatherForecastKey, weatherForecast);

    LOG.info("Stored to Redis.");
  }
}
