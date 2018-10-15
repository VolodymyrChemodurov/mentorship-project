package com.training.weather.ingestor.infrastructure.service.owm;

import com.training.weather.core.model.Coordinates;
import com.training.weather.core.model.WeatherForecast;
import com.training.weather.ingestor.infrastructure.model.owm.Clouds;
import com.training.weather.ingestor.infrastructure.model.owm.Forecast;
import com.training.weather.ingestor.infrastructure.model.owm.MainParameters;
import com.training.weather.ingestor.infrastructure.model.owm.Rain;
import com.training.weather.ingestor.infrastructure.model.owm.Snow;
import com.training.weather.ingestor.infrastructure.model.owm.Wind;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(WeatherForecastTranslator.class)
public class WeatherForecastTranslatorTest {

  private static final int DELTA = 5;

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
  private WeatherForecast weatherForecast;
  private Coordinates coordinates;

  @Mock
  private LocalDateTime currentTime;

  @Mock
  private LocalDateTime parsedTime;

  @Mock
  private Supplier<LocalDateTime> currentDateSupplier;

  @Mock
  private Function<String, LocalDateTime> owmDateParser;

  @InjectMocks
  private WeatherForecastTranslator weatherForecastTranslator;

  @Before
  public void setUp() {
    prepareTestData();
  }

  @Test
  public void shouldTranslate() {
    when(currentDateSupplier.get()).thenReturn(currentTime);
    when(owmDateParser.apply(date)).thenReturn(parsedTime);

    WeatherForecast result = weatherForecastTranslator.from(forecast, coordinates);

    assertThat(result).isEqualToComparingFieldByFieldRecursively(weatherForecast);
  }

  @Test
  public void shouldTranslateIfRainIsNull() {
    forecast.setRain(null);

    WeatherForecast weatherForecast = weatherForecastTranslator.from(forecast, coordinates);

    assertEquals(weatherForecast.getRainVolume(), 0, DELTA);
  }

  @Test
  public void shouldTranslateIfSnowIsNull() {
    forecast.setSnow(null);

    WeatherForecast weatherForecast = weatherForecastTranslator.from(forecast, coordinates);

    assertEquals(weatherForecast.getSnowVolume(), 0, DELTA);
  }

  @Test
  public void shouldTranslateIfCloudsIsNull() {
    forecast.setClouds(null);

    WeatherForecast weatherForecast = weatherForecastTranslator.from(forecast, coordinates);

    assertEquals(weatherForecast.getCloudsVolume(), 0, DELTA);
  }

  @Test
  public void shouldTranslateIfWindIsNull() {
    forecast.setWind(null);

    WeatherForecast weatherForecast = weatherForecastTranslator.from(forecast, coordinates);

    assert (weatherForecast.getWindDegree() == 0) & (weatherForecast.getWindSpeed() == 0);
  }

  private void prepareTestData() {
    prepareForecast();
    prepareCoordinates();
    prepareWeatherForecast();
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

  private void prepareCoordinates() {
    coordinates = new Coordinates();
    coordinates.setLatitude(latitude);
    coordinates.setLongitude(longitude);
  }
}
