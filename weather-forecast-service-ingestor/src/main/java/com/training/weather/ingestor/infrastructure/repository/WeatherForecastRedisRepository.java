package com.training.weather.ingestor.infrastructure.repository;

import com.training.weather.ingestor.infrastructure.entity.redis.WeatherForecast;
import com.training.weather.ingestor.infrastructure.entity.redis.WeatherForecastKey;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherForecastRedisRepository implements WeatherForecastRepository {

  private StatefulRedisConnection<WeatherForecastKey, WeatherForecast> statefulRedisConnection;

  public WeatherForecastRedisRepository(
          StatefulRedisConnection<WeatherForecastKey, WeatherForecast> statefulRedisConnection) {
    this.statefulRedisConnection = statefulRedisConnection;
  }

  /**
   * Method for storing to Redis.
   *
   * @param weatherForecastKey WeatherForecastKey
   * @param weatherForecast WeatherForecast
   * @return String
   */
  public String save(WeatherForecastKey weatherForecastKey, WeatherForecast weatherForecast) {
    RedisCommands<WeatherForecastKey, WeatherForecast> redisCommands
            = statefulRedisConnection.sync();

    return redisCommands.set(weatherForecastKey, weatherForecast);
  }
}
