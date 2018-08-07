package com.training.weather.ingestor.service;

import com.training.weather.ingestor.core.entity.WeatherForecastRedisKey;
import com.training.weather.ingestor.core.entity.WeatherForecastRedisValue;
import com.training.weather.ingestor.core.entity.WeatherForecastRedisWrapper;
import com.training.weather.ingestor.core.model.owm.City;
import com.training.weather.ingestor.core.model.owm.Coordinates;
import com.training.weather.ingestor.core.model.owm.Weather;
import com.training.weather.ingestor.core.service.WeatherDataSource;
import com.training.weather.ingestor.core.service.WeatherForecastService;
import com.training.weather.ingestor.infrastructure.repository.CityRepository;
import com.training.weather.ingestor.infrastructure.service.WeatherForecastCachingFacade;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("unchecked")
public class WeatherForecastCachingFacadeTest {

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

  private static List<City> CITIES;
  private static List<WeatherForecastRedisWrapper> WEATHER_FORECAST_REDIS_WRAPPERS;

  @Mock
  private CityRepository cityRepository;

  @Mock
  private WeatherDataSource weatherDataSource;

  @Mock
  private WeatherForecastService weatherForecastService;

  @InjectMocks
  private WeatherForecastCachingFacade weatherForecastCachingFacade;

  @BeforeClass
  public static void setUp() {
    prepareTestData();
  }

  @Test
  public void refreshShouldSuccess() {
    when(cityRepository.get()).thenReturn(CITIES);
    doReturn(WEATHER_FORECAST_REDIS_WRAPPERS).when(weatherDataSource).getForecasts(any());

    weatherForecastCachingFacade.refresh();

    verify(cityRepository, times(1)).get();
    verify(weatherDataSource, times(1)).getForecasts(any());
    verify(weatherForecastService, times(1)).save(any(), any());
  }

  @Ignore
  public static void prepareTestData() {
    prepareCities();
    prepareWeatherForecastRedisWrapper();
  }

  @Ignore
  public static void prepareCities() {
    CITIES = new ArrayList<>();
    City city = new City();

    city.setName(CITY_NAME);
    city.setCountry(CITY_COUNTRY);

    Coordinates coordinates = new Coordinates();

    coordinates.setLongitude(CITY_LONGITUDE);
    coordinates.setLatitude(CITY_LATITUDE);

    city.setCoordinates(coordinates);

    CITIES.add(city);
  }

  @Ignore
  public static void prepareWeatherForecastRedisWrapper() {
    WEATHER_FORECAST_REDIS_WRAPPERS = new ArrayList<>();

    WeatherForecastRedisKey weatherForecastRedisKey = new WeatherForecastRedisKey();
    weatherForecastRedisKey.setTimestamp(TIMESTAMP);
    weatherForecastRedisKey.setCoordinates(CITIES.get(0).getCoordinates());

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
