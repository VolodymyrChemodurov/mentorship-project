package com.training.weather.ingestor.infrastructure.repository;

import com.training.weather.ingestor.core.model.WeatherForecastRedisKey;
import com.training.weather.ingestor.core.model.WeatherForecastRedisValue;
import com.training.weather.ingestor.core.model.owm.Coordinates;
import com.training.weather.ingestor.core.repository.GeoIndexedKeyValueRepository;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("WeatherForecastRedisRepository")
public class WeatherForecastRedisRepository implements
        GeoIndexedKeyValueRepository<WeatherForecastRedisKey, WeatherForecastRedisValue> {

  private final StatefulRedisConnection<WeatherForecastRedisKey,
          WeatherForecastRedisValue> connection;

  public WeatherForecastRedisRepository(
          StatefulRedisConnection<WeatherForecastRedisKey,
                  WeatherForecastRedisValue> connection) {
    this.connection = connection;
  }

  /**
   * Method for storing WeatherForecast to Redis.
   *
   * @param weatherForecastRedisKey   WeatherForecastRedisKey
   * @param weatherForecastRedisValue WeatherForecastRedisValue
   * @param coordinates               Coordinates
   */
  public void save(WeatherForecastRedisKey weatherForecastRedisKey,
                   WeatherForecastRedisValue weatherForecastRedisValue,
                   Coordinates coordinates) {
    connection.sync().geoadd(weatherForecastRedisKey,
            coordinates.getLatitude(),
            coordinates.getLongitude(),
            weatherForecastRedisValue);
  }
}
