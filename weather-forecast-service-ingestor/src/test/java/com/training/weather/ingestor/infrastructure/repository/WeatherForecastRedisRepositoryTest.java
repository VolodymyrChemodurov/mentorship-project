package com.training.weather.ingestor.infrastructure.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.weather.core.model.Coordinates;
import com.training.weather.core.model.WeatherForecast;
import com.training.weather.core.utils.DateUtils;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WeatherForecastRedisRepositoryTest {

  private final int humidity = 94;
  private final double latitude = 42.819168;
  private final double longitude = 132.822495;
  private final double temperature = 19.16;
  private final double minTemperature = 19.16;
  private final double maxTemperature = 19.16;
  private final double pressure = 1001.14;
  private final double seaLevelPressure = 1001.92;
  private final double groundLevelPressure = 1001.14;
  private final double cloudsVolume = 0.0;
  private final double windSpeed = 8.76;
  private final double windDegree = 275.001;
  private final double rainVolume = 0.012499999999996;
  private final double snowVolume = 0.0;
  private final String date = "2018-09-05 12:00:00";
  private final String created = "2018-09-04 12:00:00";

  private final String key = "20180905120000";
  private final String value = "{\"coordinates\":{\"latitude\":42.819168,\"longitude\":132.822495},\"temperature\":19.16,\"minTemperature\":19.16,\"maxTemperature\":19.16,\"pressure\":1001.14,\"seaLevelPressure\":1001.92,\"groundLevelPressure\":1001.14,\"humidity\":94,\"cloudsVolume\":0.0,\"windSpeed\":8.76,\"windDegree\":275.001,\"rainVolume\":0.012499999999996,\"snowVolume\":0.0,\"date\":{\"hour\":0,\"minute\":0,\"nano\":0,\"second\":0,\"dayOfMonth\":5,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfYear\":248,\"month\":\"SEPTEMBER\",\"monthValue\":9,\"year\":2018,\"chronology\":{\"id\":\"ISO\",\"calendarType\":\"iso8601\"}},\"created\":{\"hour\":0,\"minute\":31,\"nano\":968000000,\"second\":2,\"dayOfMonth\":2,\"dayOfWeek\":\"SUNDAY\",\"dayOfYear\":245,\"month\":\"SEPTEMBER\",\"monthValue\":9,\"year\":2018,\"chronology\":{\"id\":\"ISO\",\"calendarType\":\"iso8601\"}}}";

  private WeatherForecast weatherForecast;

  private byte weatherForecastValue[];
  private byte weatherForecastKey[];

  @Mock
  private RedisCommands<byte[], byte[]> redisCommands;

  @Mock
  private StatefulRedisConnection<byte[], byte[]> connection;

  @Mock
  private ObjectMapper mapper;

  @InjectMocks
  private WeatherForecastRedisRepository weatherForecastRedisRepository;

  @Before
  public void setUp() {
    prepareTestData();
  }

  @Test
  public void shouldStoreToRedis() throws JsonProcessingException {
    when(connection.sync()).thenReturn(redisCommands);
    when(mapper.writeValueAsString(any())).thenReturn(value);

    weatherForecastRedisRepository.save(weatherForecast);

    verify(mapper, times(1)).writeValueAsString(eq(weatherForecast));

    verify(redisCommands, times(1)).geoadd(eq(weatherForecastKey),
            eq(weatherForecast.getCoordinates().getLongitude()),
            eq(weatherForecast.getCoordinates().getLatitude()),
            eq(weatherForecastValue));

    verify(redisCommands, times(1)).expireat(eq(weatherForecastKey),
            eq(DateUtils.timestamp(DateUtils.parseWeatherMapDateString(date))));
  }

  @Test(expected = NullPointerException.class)
  public void shouldThrowNullPointerExceptionIfWeatherForecastIsNull() {
    weatherForecastRedisRepository.save(null);
  }

  @Test(expected = NullPointerException.class)
  public void shouldThrowNullPointerExceptionIfCoordinatesIsNull() {
    weatherForecast.setCoordinates(null);

    weatherForecastRedisRepository.save(weatherForecast);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentExceptionIfJsonProcessingExceptionIsThrown() throws JsonProcessingException {
    when(mapper.writeValueAsString(any())).thenThrow(JsonProcessingException.class);

    weatherForecastRedisRepository.save(weatherForecast);
  }

  private void prepareTestData() {
    prepareWeatherForecast();
    prepareWeatherForecastValue();
    prepareWeatherForecastKey();
  }

  private void prepareWeatherForecast() {
    weatherForecast = new WeatherForecast();

    Coordinates coordinates = new Coordinates();
    coordinates.setLatitude(latitude);
    coordinates.setLongitude(longitude);

    weatherForecast.setCoordinates(coordinates);
    weatherForecast.setTemperature(temperature);
    weatherForecast.setMinTemperature(minTemperature);
    weatherForecast.setMaxTemperature(maxTemperature);
    weatherForecast.setPressure(pressure);
    weatherForecast.setSeaLevelPressure(seaLevelPressure);
    weatherForecast.setGroundLevelPressure(groundLevelPressure);
    weatherForecast.setHumidity(humidity);
    weatherForecast.setCloudsVolume(cloudsVolume);
    weatherForecast.setWindSpeed(windSpeed);
    weatherForecast.setWindDegree(windDegree);
    weatherForecast.setRainVolume(rainVolume);
    weatherForecast.setSnowVolume(snowVolume);
    weatherForecast.setDate(DateUtils.parseWeatherMapDateString(date));
    weatherForecast.setCreated(DateUtils.parseWeatherMapDateString(created));
  }

  private void prepareWeatherForecastValue() {
    weatherForecastValue = value.getBytes();
  }

  private void prepareWeatherForecastKey() {
    weatherForecastKey = key.getBytes();
  }
}
