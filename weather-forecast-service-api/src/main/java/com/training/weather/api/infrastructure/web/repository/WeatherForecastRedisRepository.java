package com.training.weather.api.infrastructure.web.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.weather.api.core.repository.WeatherForecastRepository;
import com.training.weather.api.infrastructure.web.exception.Error;
import com.training.weather.api.infrastructure.web.exception.ServiceException;
import com.training.weather.core.model.Coordinates;
import com.training.weather.core.model.WeatherForecast;
import com.training.weather.core.utils.Keys;
import io.lettuce.core.GeoArgs.Unit;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class WeatherForecastRedisRepository implements WeatherForecastRepository {
  private static final Logger LOG = LoggerFactory.getLogger(WeatherForecastRedisRepository.class);

  private final int radius;

  private final StatefulRedisConnection<byte[], byte[]> connection;

  private final ObjectMapper objectMapper;

  /**
   * Constructor.
   *
   * @param radius       lookup radius.
   * @param connection   {@link StatefulRedisConnection}.
   * @param objectMapper {@link ObjectMapper}.
   */
  public WeatherForecastRedisRepository(int radius,
                                        StatefulRedisConnection<byte[], byte[]> connection,
                                        ObjectMapper objectMapper) {
    this.radius = radius;
    this.connection = connection;
    this.objectMapper = objectMapper;
  }

  /**
   * Method for retrieving list of WeatherForecasts.
   *
   * @param coordinates   {@link Coordinates}.
   * @param localDateTime forecast time {@link LocalDateTime}.
   * @return {@link Set&ltWeatherForecast&gt}.
   */
  public Set<WeatherForecast> getForecastsByCoordinates(Coordinates coordinates,
                                                        LocalDateTime localDateTime) {
    RedisCommands<byte[], byte[]> redisCommands = connection.sync();

    String key = Keys.key(localDateTime);

    Set<byte[]> weatherForecasts = redisCommands.georadius(key.getBytes(),
            coordinates.getLongitude(),
            coordinates.getLatitude(),
            radius,
            Unit.km);

    return weatherForecasts.stream()
            .filter(Objects::nonNull)
            .map(this::convert).collect(Collectors.toSet());
  }

  private WeatherForecast convert(byte[] weatherForecast) {
    try {
      return objectMapper.readValue(weatherForecast, WeatherForecast.class);
    } catch (IOException e) {
      LOG.error("Error reading bytes into forecast object:" + e.getMessage());
      throw new ServiceException(Error.INTERNAL_ERROR);
    }
  }
}
