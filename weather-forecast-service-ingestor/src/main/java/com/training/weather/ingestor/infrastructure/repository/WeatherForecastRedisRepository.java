package com.training.weather.ingestor.infrastructure.repository;

import com.training.weather.ingestor.core.entity.WeatherForecastRedisKey;
import com.training.weather.ingestor.core.entity.WeatherForecastRedisValue;
import com.training.weather.ingestor.core.model.owm.Coordinates;
import com.training.weather.ingestor.core.repository.GeoIndexedKeyValueRepository;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherForecastRedisRepository implements
        GeoIndexedKeyValueRepository<WeatherForecastRedisKey, WeatherForecastRedisValue> {

  private final StatefulRedisConnection<WeatherForecastRedisKey,
          WeatherForecastRedisValue> statefulRedisConnection;

  public WeatherForecastRedisRepository(
          StatefulRedisConnection<WeatherForecastRedisKey,
                  WeatherForecastRedisValue> statefulRedisConnection) {
    this.statefulRedisConnection = statefulRedisConnection;
  }

  /**
   * Method for storing to Redis.
   *
   * @param weatherForecastRedisKey   WeatherForecastRedisKey
   * @param weatherForecastRedisValue WeatherForecastRedisValue
   * @param coordinates               Coordinates
   */
  public void save(WeatherForecastRedisKey weatherForecastRedisKey,
                   WeatherForecastRedisValue weatherForecastRedisValue,
                   Coordinates coordinates) {
    RedisCommands<WeatherForecastRedisKey, WeatherForecastRedisValue> redisCommands
            = statefulRedisConnection.sync();

    redisCommands.geoadd(weatherForecastRedisKey,
            coordinates.getLatitude(),
            coordinates.getLongitude(),
            weatherForecastRedisValue);
  }
}
