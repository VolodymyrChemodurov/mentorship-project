package com.training.weather.ingestor.infrastructure.service.owm;

import com.training.weather.core.model.Coordinates;
import com.training.weather.ingestor.infrastructure.model.owm.Clouds;
import com.training.weather.ingestor.infrastructure.model.owm.Forecast;
import com.training.weather.ingestor.infrastructure.model.owm.MainParameters;
import com.training.weather.ingestor.infrastructure.model.owm.Rain;
import com.training.weather.ingestor.infrastructure.model.owm.Snow;
import com.training.weather.ingestor.infrastructure.model.owm.Wind;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class WeatherForecastTranslatorTest {

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

  private Forecast forecast;

  private Coordinates coordinates;

  @Before
  public void setUp() {
    prepareTestData();
  }

  @Test
  public void shouldTranslate() {
    WeatherForecastTranslator.from(forecast, coordinates);
  }

  @Test
  public void shouldTranslateIfRainIsNull() {
    forecast.setRain(null);
    WeatherForecastTranslator.from(forecast, coordinates);
  }

  @Test
  public void shouldTranslateIfSnowIsNull() {
    forecast.setSnow(null);
    WeatherForecastTranslator.from(forecast, coordinates);
  }

  @Test
  public void shouldTranslateIfCloudsIsNull() {
    forecast.setClouds(null);
    WeatherForecastTranslator.from(forecast, coordinates);
  }

  @Test
  public void shouldTranslateIfWindIsNull() {
    forecast.setWind(null);
    WeatherForecastTranslator.from(forecast, coordinates);
  }

  @Test(expected = NullPointerException.class)
  public void shouldThrowNullPointerExceptionIfForecastIsNull() {
    WeatherForecastTranslator.from(null, coordinates);
  }

  @Test(expected = NullPointerException.class)
  public void shouldThrowNullPointerExceptionIfMainParametersIsNull() {
    forecast.setMainParameters(null);
    WeatherForecastTranslator.from(forecast, null);
  }

  private void prepareTestData() {
    prepareForecast();
    prepareCoordinates();
  }

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

  private void prepareCoordinates() {
    coordinates = new Coordinates();
    coordinates.setLatitude(latitude);
    coordinates.setLongitude(longitude);
  }
}
