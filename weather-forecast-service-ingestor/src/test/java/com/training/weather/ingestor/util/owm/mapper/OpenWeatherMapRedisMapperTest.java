package com.training.weather.ingestor.util.owm.mapper;

import com.training.weather.ingestor.core.model.WeatherForecastKeyValueWrapper;
import com.training.weather.ingestor.core.model.WeatherForecastRedisKey;
import com.training.weather.ingestor.core.model.WeatherForecastRedisValue;
import com.training.weather.ingestor.core.model.WeatherForecastRedisWrapper;
import com.training.weather.ingestor.core.model.WeatherForecastWrapper;
import com.training.weather.ingestor.core.model.owm.City;
import com.training.weather.ingestor.core.model.owm.Clouds;
import com.training.weather.ingestor.core.model.owm.Coordinates;
import com.training.weather.ingestor.core.model.owm.Forecast;
import com.training.weather.ingestor.core.model.owm.MainParameters;
import com.training.weather.ingestor.core.model.owm.Rain;
import com.training.weather.ingestor.core.model.owm.Snow;
import com.training.weather.ingestor.core.model.owm.Weather;
import com.training.weather.ingestor.core.model.owm.Wind;
import com.training.weather.ingestor.infrastructure.model.owm.OpenWeatherMapResponse;
import com.training.weather.ingestor.infrastructure.util.mapper.OpenWeatherMapRedisMapper;
import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("unchecked")
public class OpenWeatherMapRedisMapperTest {

  private static final double ZERO_DOUBLE = 0.0;
  private static final int ZERO_INTEGER = 0;

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
  private static OpenWeatherMapResponse OWM_RESPONSE;
  private static List<WeatherForecastKeyValueWrapper> WEATHER_FORECAST_REDIS_WRAPPERS;

  @InjectMocks
  private OpenWeatherMapRedisMapper openWeatherMapRedisMapper;

  @BeforeClass
  public static void setUp() {
    prepareTestData();
  }

  @Test
  public void mapShouldSuccess(){
    List<WeatherForecastWrapper> result = openWeatherMapRedisMapper.map(OWM_RESPONSE);

    Assertions.assertThat(WEATHER_FORECAST_REDIS_WRAPPERS.get(0)).
            isEqualToComparingFieldByFieldRecursively(result.get(0));
  }

  @Test
  public void mapShouldReturnSuccessIfCloudsEqualNull(){
    OWM_RESPONSE.getForecasts().get(0).setClouds(null);


    List<WeatherForecastRedisWrapper> result = openWeatherMapRedisMapper.map(OWM_RESPONSE);

    assertEquals(ZERO_DOUBLE, result.get(0).getValue().getCloudsVolume(), ZERO_DOUBLE);
  }

  @Test
  public void mapShouldReturnSuccessIfRainEqualNull(){
    OWM_RESPONSE.getForecasts().get(0).setRain(null);


    List<WeatherForecastRedisWrapper> result = openWeatherMapRedisMapper.map(OWM_RESPONSE);

    assertEquals(ZERO_DOUBLE, result.get(0).getValue().getRainVolume(), ZERO_DOUBLE);
  }

  @Test
  public void mapShouldReturnSuccessISnowEqualNull(){
    OWM_RESPONSE.getForecasts().get(0).setSnow(null);


    List<WeatherForecastRedisWrapper> result = openWeatherMapRedisMapper.map(OWM_RESPONSE);

    assertEquals(ZERO_DOUBLE, result.get(0).getValue().getSnowVolume(), ZERO_DOUBLE);
  }

  @Ignore
  public static void prepareTestData() {
    prepareCity();
    prepareOwmResponse();
    prepareWeatherForecastRedisWrapper();
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
  public static void prepareOwmResponse(){
    OWM_RESPONSE = new OpenWeatherMapResponse();

    MainParameters mainParameters = new MainParameters();
    mainParameters.setTemperature(TEMPERATURE);
    mainParameters.setMinTemperature(MIN_TEMPERATURE);
    mainParameters.setMaxTemperature(MAX_TEMPERATURE);
    mainParameters.setPressure(PRESSURE);
    mainParameters.setSeaLevelPressure(SEA_LEVEL_PRESSURE);
    mainParameters.setGroundLevelPressure(GROUND_LEVEL_PRESSURE);
    mainParameters.setHumidity(HUMIDITY);

    Weather weather = new Weather();
    weather.setId(ID);
    weather.setMain(MAIN);
    weather.setDescription(DESCRIPTION);
    weather.setIcon(ICON);

    Clouds clouds = new Clouds();
    clouds.setVolume(CLOUDS_VOLUME);

    Wind wind = new Wind();
    wind.setDegree(WIND_DEGREE);
    wind.setSpeed(WIND_SPEED);

    Rain rain = new Rain();
    rain.setVolume(RAIN_VOLUME);

    Snow snow = new Snow();
    snow.setVolume(SNOW_VOLUME);

    Forecast forecast = new Forecast();
    forecast.setTimestamp(TIMESTAMP);
    forecast.setMainParameters(mainParameters);
    forecast.setWeather(Arrays.asList(weather));
    forecast.setClouds(clouds);
    forecast.setRain(rain);
    forecast.setSnow(snow);
    forecast.setWind(wind);
    forecast.setDate(DATE);

    OWM_RESPONSE.setCoordinates(CITY.getCoordinates());
    OWM_RESPONSE.setForecasts(Arrays.asList(forecast));
  }

  @Ignore
  public static void prepareWeatherForecastRedisWrapper() {
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
