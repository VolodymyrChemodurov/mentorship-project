package com.training.weather.ingestor.repository;

import com.training.weather.ingestor.core.entity.WeatherForecastRedisKey;
import com.training.weather.ingestor.core.entity.WeatherForecastRedisValue;
import com.training.weather.ingestor.core.model.owm.Coordinates;
import com.training.weather.ingestor.core.model.owm.Weather;
import com.training.weather.ingestor.infrastructure.repository.WeatherForecastRedisRepository;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherForecastRedisRepositoryTest {

  private static final long REDIS_COMMANDS_SAVE_RESPONSE = 1L;
  private static final double LONGITUDE = 1.1111;
  private static final double LATITUDE = 1.1111;
  private static final int TIMESTAMP = 1;

  private static final double TEMPERATURE = 1.1;
  private static final double MIN_TEMPERATURE = 1.1;
  private static final double MAX_TEMPERATURE = 1.1;
  private static final double PRESSURE = 1.1;
  private static final double SEA_LEVEL_PRESSURE = 1.1;
  private static final double GROUND_LEVEL_PRESSURE = 1.1;
  private static final int HUMITIDY = 1;
  private static final List<Weather> WEATHERS = new ArrayList<>();
  private static final double CLOUDS_VOLUME = 1.1;
  private static final double WIND_SPEED = 1.1;
  private static final double WIND_DEGREE = 1.1;
  private static final double RAIN_VOLUME = 1.1;
  private static final double SNOW_VOLUME = 1.1;
  private static final String DATE = "1111111";

  private static Coordinates COORDINATES;
  private static WeatherForecastRedisKey WEATHER_FORECAST_REDIS_KEY;
  private static WeatherForecastRedisValue WEATHER_FORECAST_REDIS_VALUE;

  @Mock
  private StatefulRedisConnection<WeatherForecastRedisKey,
          WeatherForecastRedisValue> statefulRedisConnection;

  @Mock
  private RedisCommands<WeatherForecastRedisKey,
          WeatherForecastRedisValue> redisCommand;

  @InjectMocks
  private WeatherForecastRedisRepository weatherForecastRedisRepository;

  @BeforeClass
  public static void setUp() {
    prepareWeatherForecastRedisKey();
    prepareWeatherForecastRedisValue();
  }

  @Test
  public void saveShouldSuccess() {
    when(statefulRedisConnection.sync()).thenReturn(redisCommand);

    when(redisCommand.geoadd(any(WeatherForecastRedisKey.class),
            anyDouble(),
            anyDouble(),
            any(WeatherForecastRedisValue.class))).thenReturn(REDIS_COMMANDS_SAVE_RESPONSE);

    weatherForecastRedisRepository.save(WEATHER_FORECAST_REDIS_KEY, WEATHER_FORECAST_REDIS_VALUE, COORDINATES);

    verify(statefulRedisConnection, times(1)).sync();
    verify(redisCommand, times(1)).geoadd(eq(WEATHER_FORECAST_REDIS_KEY),
            eq(LATITUDE),
            eq(LONGITUDE),
            eq(WEATHER_FORECAST_REDIS_VALUE));
  }

  @Ignore
  private static void prepareWeatherForecastRedisKey() {
    WEATHER_FORECAST_REDIS_KEY = new WeatherForecastRedisKey();

    COORDINATES = new Coordinates();
    COORDINATES.setLatitude(LATITUDE);
    COORDINATES.setLongitude(LONGITUDE);

    WEATHER_FORECAST_REDIS_KEY.setCoordinates(COORDINATES);
    WEATHER_FORECAST_REDIS_KEY.setTimestamp(TIMESTAMP);
  }

  @Ignore
  private static void prepareWeatherForecastRedisValue() {
    WEATHER_FORECAST_REDIS_VALUE = new WeatherForecastRedisValue();

    WEATHER_FORECAST_REDIS_VALUE.setTimestamp(TIMESTAMP);
    WEATHER_FORECAST_REDIS_VALUE.setTemperature(TEMPERATURE);
    WEATHER_FORECAST_REDIS_VALUE.setMinTemperature(MIN_TEMPERATURE);
    WEATHER_FORECAST_REDIS_VALUE.setMaxTemperature(MAX_TEMPERATURE);
    WEATHER_FORECAST_REDIS_VALUE.setPressure(PRESSURE);
    WEATHER_FORECAST_REDIS_VALUE.setSeaLevelPressure(SEA_LEVEL_PRESSURE);
    WEATHER_FORECAST_REDIS_VALUE.setGroundLevelPressure(GROUND_LEVEL_PRESSURE);
    WEATHER_FORECAST_REDIS_VALUE.setHumidity(HUMITIDY);
    WEATHER_FORECAST_REDIS_VALUE.setWeather(WEATHERS);
    WEATHER_FORECAST_REDIS_VALUE.setCloudsVolume(CLOUDS_VOLUME);
    WEATHER_FORECAST_REDIS_VALUE.setWindSpeed(WIND_SPEED);
    WEATHER_FORECAST_REDIS_VALUE.setWindDegree(WIND_DEGREE);
    WEATHER_FORECAST_REDIS_VALUE.setRainVolume(RAIN_VOLUME);
    WEATHER_FORECAST_REDIS_VALUE.setSnowVolume(SNOW_VOLUME);
    WEATHER_FORECAST_REDIS_VALUE.setDate(DATE);
  }
}
