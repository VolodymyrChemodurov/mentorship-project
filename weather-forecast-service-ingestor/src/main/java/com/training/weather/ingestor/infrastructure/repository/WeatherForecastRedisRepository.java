package com.training.weather.ingestor.infrastructure.repository;

import com.training.weather.ingestor.core.WeatherForecastRepository;
import com.training.weather.ingestor.core.model.WeatherForecast;
import com.training.weather.ingestor.core.model.WeatherForecastKey;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherForecastRedisRepository implements WeatherForecastRepository {

  private StatefulRedisConnection<WeatherForecastKey, WeatherForecast> connection;

  public WeatherForecastRedisRepository(
          StatefulRedisConnection<WeatherForecastKey, WeatherForecast> connection) {
    this.connection = connection;
  }

  /**
   * Method for storing WeatherForecast to Redis.
   */
  public String save(
          WeatherForecastKey weatherForecastKey,
          WeatherForecast weatherForecast) {
    return connection.sync().set(weatherForecastKey, weatherForecast);
  }
}
