package com.training.weather.ingestor.service;

import com.training.weather.ingestor.core.entity.WeatherForecastRedisKey;
import com.training.weather.ingestor.core.entity.WeatherForecastRedisValue;
import com.training.weather.ingestor.core.entity.WeatherForecastRedisWrapper;
import com.training.weather.ingestor.core.entity.WeatherForecastWrapper;
import com.training.weather.ingestor.core.model.owm.City;
import com.training.weather.ingestor.core.model.owm.Coordinates;
import com.training.weather.ingestor.core.model.owm.Weather;
import com.training.weather.ingestor.infrastructure.model.owm.OwmResponse;
import com.training.weather.ingestor.infrastructure.service.OpenWeatherMapDataSource;
import com.training.weather.ingestor.infrastructure.util.Mapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("unchecked")
public class OpenWeatherMapDataSourceTest {

  private static final String API_SCHEME = "http";
  private static final String API_HOST = "api.openweathermap.org";
  private static final String API_KEY = "a5b6e7041392ba7156b0d1de0e3e7923";

  private static final String CITY_NAME = "Lviv";
  private static final String CITY_COUNTRY = "UA";
  private static final double CITY_LONGITUDE = 12.3;
  private static final double CITY_LATITUDE = 12.3;

  private static final int ID = 1;
  private static final int HUMIDITY = 1;
  private static final int TIMESTAMP = 11111;
  private static final double TEMPERATURE = 1.2;
  private static final double MIN_TEMPERATURE = 1.2;
  private static final double MAX_TEMPERATURE = 1.2;
  private static final double PRESSURE = 1.2;
  private static final double SEA_LEVEL_PRESSURE = 1.2;
  private static final double GROUND_LEVEL_PRESSURE = 1.2;
  private static final double CLOUDS_VOLUME = 12.3;
  private static final double WIND_SPEED = 12.3;
  private static final double WIND_DEGREE = 12.3;
  private static final double RAIN_VOLUME = 12.3;
  private static final double SNOW_VOLUME = 12.3;
  private static final String DATE = "1212312";
  private static final String MAIN = "main";
  private static final String DESCRIPTION = "description";
  private static final String ICON = "icon link";

  private static City CITY;
  private static List<WeatherForecastRedisWrapper> WEATHER_FORECAST_REDIS_WRAPPERS;

  @Mock
  private OwmResponse owmResponse;

  @Mock
  private Mapper<OwmResponse> mapper;

  @Mock
  private RestTemplate restTemplate;

  @Mock
  private ResponseEntity<OwmResponse> owmResponseResponseEntity;

  @InjectMocks
  private OpenWeatherMapDataSource openWeatherMapDataSource;

  @Before
  public void before() throws NoSuchFieldException, IllegalAccessException {
    Field modifiersField = Field.class.getDeclaredField("modifiers");
    modifiersField.setAccessible(true);

    Class clazz = openWeatherMapDataSource.getClass();
    Field apiScheme = clazz.getDeclaredField("apiScheme");
    apiScheme.setAccessible(true);
    Field apiHost = clazz.getDeclaredField("apiHost");
    apiHost.setAccessible(true);
    Field apiKey = clazz.getDeclaredField("apiKey");
    apiKey.setAccessible(true);
    modifiersField.setInt(apiScheme, apiScheme.getModifiers() & ~Modifier.FINAL);
    apiScheme.set(openWeatherMapDataSource, API_SCHEME);
    apiScheme.setAccessible(false);
    modifiersField.setInt(apiHost, apiHost.getModifiers() & ~Modifier.FINAL);
    apiHost.set(openWeatherMapDataSource, API_HOST);
    apiHost.setAccessible(false);
    modifiersField.setInt(apiKey, apiKey.getModifiers() & ~Modifier.FINAL);
    apiKey.set(openWeatherMapDataSource, API_KEY);
    apiKey.setAccessible(false);
  }

  @BeforeClass
  public static void setUp() {
    prepareTestData();
  }

  @Test
  public void getForecastsShouldReturnSuccess() {
    when(restTemplate.getForEntity(any(URI.class), any(Class.class))).
            thenReturn(owmResponseResponseEntity);
    doReturn(owmResponse).when(owmResponseResponseEntity).getBody();
    doReturn(WEATHER_FORECAST_REDIS_WRAPPERS).when(mapper).map(owmResponse);

    List<WeatherForecastWrapper> wrappers = openWeatherMapDataSource.getForecasts(CITY);

    assertEquals(WEATHER_FORECAST_REDIS_WRAPPERS, wrappers);

    verify(restTemplate, times(1)).getForEntity(any(URI.class), any(Class.class));
    verify(owmResponseResponseEntity, times(1)).getBody();
    verify(mapper, times(1)).map(owmResponse);
  }

  @Ignore
  public static void prepareTestData() {
    prepareCity();
    prepapreWeatherForecastRedisWrapper();
  }

  @Ignore
  public static void prepareCity() {
    CITY = new City();

    CITY.setName(CITY_NAME);
    CITY.setCountry(CITY_COUNTRY);

    Coordinates coordinates = new Coordinates();

    coordinates.setLongitude(CITY_LONGITUDE);
    coordinates.setLatitude(CITY_LATITUDE);

    CITY.setCoordinates(coordinates);
  }

  @Ignore
  public static void prepapreWeatherForecastRedisWrapper() {
    WEATHER_FORECAST_REDIS_WRAPPERS = new ArrayList<>();

    WeatherForecastRedisKey weatherForecastRedisKey = new WeatherForecastRedisKey();
    weatherForecastRedisKey.setTimestamp(TIMESTAMP);
    weatherForecastRedisKey.setCoordinates(CITY.getCoordinates());

    WeatherForecastRedisValue weatherForecastRedisValue = new WeatherForecastRedisValue();
    weatherForecastRedisValue.setTimestamp(TIMESTAMP);
    weatherForecastRedisValue.setTemperature(TEMPERATURE);
    weatherForecastRedisValue.setMinTemperature(MIN_TEMPERATURE);
    weatherForecastRedisValue.setMaxTemperature(MAX_TEMPERATURE);
    weatherForecastRedisValue.setPressure(PRESSURE);
    weatherForecastRedisValue.setSeaLevelPressure(SEA_LEVEL_PRESSURE);
    weatherForecastRedisValue.setGroundLevelPressure(GROUND_LEVEL_PRESSURE);
    weatherForecastRedisValue.setHumidity(HUMIDITY);
    weatherForecastRedisValue.setCloudsVolume(CLOUDS_VOLUME);
    weatherForecastRedisValue.setWindSpeed(WIND_SPEED);
    weatherForecastRedisValue.setWindDegree(WIND_DEGREE);
    weatherForecastRedisValue.setRainVolume(RAIN_VOLUME);
    weatherForecastRedisValue.setSnowVolume(SNOW_VOLUME);
    weatherForecastRedisValue.setDate(DATE);

    Weather weather = new Weather();
    weather.setId(ID);
    weather.setMain(MAIN);
    weather.setDescription(DESCRIPTION);
    weather.setIcon(ICON);

    weatherForecastRedisValue.setWeather(Arrays.asList(weather));

    WeatherForecastRedisWrapper weatherForecastRedisWrapper = new WeatherForecastRedisWrapper();
    weatherForecastRedisWrapper.setKey(weatherForecastRedisKey);
    weatherForecastRedisWrapper.setValue(weatherForecastRedisValue);

    WEATHER_FORECAST_REDIS_WRAPPERS.add(weatherForecastRedisWrapper);
  }
}
