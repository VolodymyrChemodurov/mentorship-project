package com.training.weather.ingestor.infrastructure.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.weather.ingestor.core.model.Coordinates;
import com.training.weather.ingestor.core.model.WeatherForecast;
import com.training.weather.ingestor.core.repository.WeatherForecastRepository;
import io.lettuce.core.api.StatefulRedisConnection;
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

    connection.sync().geoadd(
        weatherForecast.getDate().getBytes(),
        coordinates.getLongitude(),
        coordinates.getLatitude(),
        convert(weatherForecast).getBytes());
  }

  private String convert(WeatherForecast weatherForecast) {
    try {
      return mapper.writeValueAsString(weatherForecast);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
