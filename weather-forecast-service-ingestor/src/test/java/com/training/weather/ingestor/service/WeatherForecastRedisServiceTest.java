package com.training.weather.ingestor.service;

import com.training.weather.ingestor.core.entity.WeatherForecastRedisKey;
import com.training.weather.ingestor.core.entity.WeatherForecastRedisValue;
import com.training.weather.ingestor.core.entity.WeatherForecastRedisWrapper;
import com.training.weather.ingestor.core.model.owm.City;
import com.training.weather.ingestor.core.model.owm.Coordinates;
import com.training.weather.ingestor.core.model.owm.Weather;
import com.training.weather.ingestor.core.repository.GeoIndexedKeyValueRepository;
import com.training.weather.ingestor.infrastructure.service.WeatherForecastRedisService;
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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("unchecked")
public class WeatherForecastRedisServiceTest {

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
  private static WeatherForecastRedisWrapper WEATHER_FORECAST_REDIS_WRAPPER;

  @Mock
  private GeoIndexedKeyValueRepository repository;

  @InjectMocks
  private WeatherForecastRedisService weatherForecastRedisService;

  @BeforeClass
  public static void setUp(){
    prepareTestData();
  }

  @Test
  public void saveShouldSuccess(){
    weatherForecastRedisService.save(WEATHER_FORECAST_REDIS_WRAPPER, CITY);

    verify(repository, times(1)).save(
            WEATHER_FORECAST_REDIS_WRAPPER.getKey(),
            WEATHER_FORECAST_REDIS_WRAPPER.getValue(),
            CITY.getCoordinates()
    );
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

    WEATHER_FORECAST_REDIS_WRAPPER = new WeatherForecastRedisWrapper();
    WEATHER_FORECAST_REDIS_WRAPPER.setKey(weatherForecastRedisKey);
    WEATHER_FORECAST_REDIS_WRAPPER.setValue(weatherForecastRedisValue);
    }
}
