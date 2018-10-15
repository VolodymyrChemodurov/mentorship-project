package com.training.weather.ingestor.infrastructure.service.owm;

import com.training.weather.core.model.Coordinates;
import com.training.weather.core.model.WeatherForecast;
import com.training.weather.ingestor.core.model.City;
import com.training.weather.ingestor.infrastructure.model.owm.Clouds;
import com.training.weather.ingestor.infrastructure.model.owm.Forecast;
import com.training.weather.ingestor.infrastructure.model.owm.MainParameters;
import com.training.weather.ingestor.infrastructure.model.owm.OpenWeatherMapResponse;
import com.training.weather.ingestor.infrastructure.model.owm.Rain;
import com.training.weather.ingestor.infrastructure.model.owm.Snow;
import com.training.weather.ingestor.infrastructure.model.owm.Wind;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
@RunWith(PowerMockRunner.class)
@PrepareForTest({OpenWeatherMapForecastDataSource.class})
public class OpenWeatherMapForecastDataSourceTest {

  private static final String apiScheme = "testscheme";

  private static final String apiHost = "testapihsot";

  private static final String apiKey = "tesapikey";

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
  private final String date = "2018-09-02 21:00:00";
  private final String cityName = "test city";
  private final String countryName = "test country";

  private City city;
  private Forecast forecast;
  private WeatherForecast weatherForecast;
  private Coordinates coordinates;

  private OpenWeatherMapResponse openWeatherMapResponse;

  @Mock
  private LocalDateTime currentTime;

  @Mock
  private LocalDateTime parsedTime;

  @Mock
  private ResponseEntity<OpenWeatherMapResponse> responseEntity;

  @Mock
  private RestTemplate restTemplate;

  @Mock
  private WeatherForecastTranslator weatherForecastTranslator;

  private OpenWeatherMapForecastDataSource openWeatherMapForecastDataSource;

  @Before
  public void setUp() {
    openWeatherMapForecastDataSource = new OpenWeatherMapForecastDataSource(apiScheme,
            apiHost,
            apiKey,
            restTemplate,
            weatherForecastTranslator);

    prepareTestData();
  }

  @Test
  public void getForecastsShouldReturnSuccessfully() {
    when(restTemplate.getForEntity(any(URI.class), any(Class.class))).thenReturn(responseEntity);
    when(responseEntity.getBody()).thenReturn(openWeatherMapResponse);
    when(weatherForecastTranslator.from(forecast, coordinates)).thenReturn(weatherForecast);

    List<WeatherForecast> result = openWeatherMapForecastDataSource.getForecasts(city);

    assertEquals(weatherForecast, result.get(0));
  }

  @Test
  public void getForecastsShouldReturnEmptyArrayIfOWMReturnedNOResponse() {
    when(restTemplate.getForEntity(any(URI.class), any(Class.class))).thenReturn(responseEntity);
    when(responseEntity.getBody()).thenReturn(openWeatherMapResponse);

    openWeatherMapResponse.setForecasts(null);

    List<WeatherForecast> result = openWeatherMapForecastDataSource.getForecasts(city);

    assertTrue(result.isEmpty());
  }

  @Ignore
  private void prepareTestData() {
    prepareForecast();
    prepareCoordinates();
    prepareWeatherForecast();
    prepareCity();
    prepareOpenWeatherMapResponse();
  }

  @Ignore
  private void prepareForecast() {
    forecast = new Forecast();

    MainParameters mainParameters = new MainParameters();
    mainParameters.setGroundLevelPressure(groundLevelPressure);
    mainParameters.setSeaLevelPressure(seaLevelPressure);
    mainParameters.setHumidity(humidity);
    mainParameters.setMaxTemperature(maxTemperature);
    mainParameters.setMinTemperature(minTemperature);
    mainParameters.setPressure(pressure);
    mainParameters.setTemperature(temperature);

    Wind wind = new Wind();
    wind.setDegree(windDegree);
    wind.setSpeed(windSpeed);

    Rain rain = new Rain();
    rain.setVolume(rainVolume);

    Snow snow = new Snow();
    snow.setVolume(snowVolume);

    Clouds clouds = new Clouds();
    clouds.setVolume(cloudsVolume);

    forecast.setMainParameters(mainParameters);
    forecast.setWind(wind);
    forecast.setRain(rain);
    forecast.setSnow(snow);
    forecast.setClouds(clouds);
    forecast.setDate(date);
  }

  @Ignore
  private void prepareWeatherForecast() {
    weatherForecast = new WeatherForecast();

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
    weatherForecast.setCreated(currentTime);
    weatherForecast.setDate(parsedTime);
  }

  @Ignore
  private void prepareCoordinates() {
    coordinates = new Coordinates();
    coordinates.setLatitude(latitude);
    coordinates.setLongitude(longitude);
  }

  @Ignore
  private void prepareCity() {
    city = new City();

    city.setName(cityName);
    city.setCountry(countryName);
    city.setCoordinates(coordinates);
  }

  @Ignore
  private void prepareOpenWeatherMapResponse() {
    openWeatherMapResponse = new OpenWeatherMapResponse();

    openWeatherMapResponse.setForecasts(Arrays.asList(forecast));
  }
}
