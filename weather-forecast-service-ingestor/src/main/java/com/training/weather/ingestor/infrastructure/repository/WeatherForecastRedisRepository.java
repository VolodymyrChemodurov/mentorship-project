package com.training.weather.ingestor.infrastructure.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.weather.core.model.Coordinates;
import com.training.weather.core.model.WeatherForecast;
import com.training.weather.core.repository.WeatherForecastRepository;
import com.training.weather.core.utils.DateUtils;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherForecastRedisRepository implements WeatherForecastRepository {

  private final StatefulRedisConnection<byte[], byte[]> connection;
  private final ObjectMapper mapper;

  public WeatherForecastRedisRepository(
          StatefulRedisConnection<byte[], byte[]> connection,
          ObjectMapper mapper) {
    this.connection = connection;
    this.mapper = mapper;
  }

  /**
   * Method for storing WeatherForecast to Redis.
   */
  public void save(WeatherForecast weatherForecast) {
    Coordinates coordinates = weatherForecast.getCoordinates();

    String key = DateUtils.toWeatherForecastDateString(weatherForecast.getDate());
    long expiryTime = DateUtils.timestamp(weatherForecast.getDate());

    RedisCommands commands = connection.sync();

    commands.geoadd(
            key.getBytes(),
            coordinates.getLongitude(),
            coordinates.getLatitude(),
            convert(weatherForecast).getBytes());

    commands.expireat(key.getBytes(), expiryTime);
  }

  private String convert(WeatherForecast weatherForecast) {
    try {
      return mapper.writeValueAsString(weatherForecast);
    } catch (JsonProcessingException jpException) {
      throw new IllegalArgumentException(jpException);
    }
  }
}
