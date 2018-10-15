package com.training.weather.api.infrastructure.web.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.weather.api.infrastructure.web.exception.ServiceException;
import com.training.weather.core.model.Coordinates;
import com.training.weather.core.model.WeatherForecast;
import com.training.weather.core.utils.Keys;
import io.lettuce.core.GeoArgs.Unit;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Keys.class, WeatherForecast.class, LocalDateTime.class})
public class WeatherForecastRedisRepositoryTest {

  private static final int RADIUS = 10;
  private static final String DATE_TIME = "10041999";
  private static final double LATITUDE = 10;
  private static final double LONGITUDE = 10;

  private Coordinates coordinates;

  private Set<byte[]> bytesSet;
  private Set<WeatherForecast> weatherForecasts;

  private byte[] bytes = new byte[]{};

  @Mock
  private LocalDateTime dateTime;

  @Mock
  private WeatherForecast weatherForecast;

  @Mock
  private RedisCommands<byte[], byte[]> redisCommands;

  @Mock
  private StatefulRedisConnection<byte[], byte[]> connection;

  @Mock
  private ObjectMapper objectMapper;

  private WeatherForecastRedisRepository weatherForecastRedisRepository;

  @Before
  public void setUp() {
    weatherForecastRedisRepository = new WeatherForecastRedisRepository(RADIUS,
            connection,
            objectMapper);

    prepareTestData();
  }

  @Test
  public void shouldReturnSuccessfully() throws IOException {
    mockStatic(Keys.class);
    when(Keys.key(dateTime)).thenReturn(DATE_TIME);

    when(connection.sync()).thenReturn(redisCommands);
    when(redisCommands.georadius(DATE_TIME.getBytes(),
            LONGITUDE,
            LATITUDE,
            RADIUS,
            Unit.km)).thenReturn(bytesSet);
    when(objectMapper.readValue(bytes, WeatherForecast.class)).thenReturn(weatherForecast);

    Set<WeatherForecast> result = weatherForecastRedisRepository.getForecastsByCoordinates(coordinates, dateTime);

    assertEquals(weatherForecasts, result);
  }

  @Test(expected = ServiceException.class)
  public void shouldThrowServiceExceptionIfMappingFailure() throws IOException {
    mockStatic(Keys.class);
    when(Keys.key(dateTime)).thenReturn(DATE_TIME);

    when(connection.sync()).thenReturn(redisCommands);
    when(redisCommands.georadius(DATE_TIME.getBytes(),
            LONGITUDE,
            LATITUDE,
            RADIUS,
            Unit.km)).thenReturn(bytesSet);
    when(objectMapper.readValue(bytes, WeatherForecast.class)).thenThrow(IOException.class);

    weatherForecastRedisRepository.getForecastsByCoordinates(coordinates, dateTime);
  }

  private void prepareTestData() {
    prepareCoordinates();
    prepareBytesSet();
    prepareWeatherForecastSet();
  }

  private void prepareCoordinates() {
    coordinates = new Coordinates();

    coordinates.setLatitude(LATITUDE);
    coordinates.setLongitude(LONGITUDE);
  }

  private void prepareBytesSet() {
    bytesSet = new HashSet<>();

    bytesSet.add(bytes);
  }

  private void prepareWeatherForecastSet() {
    weatherForecasts = new HashSet<>();

    weatherForecasts.add(weatherForecast);
  }
}
