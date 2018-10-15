package com.training.weather.ingestor.core.service;

import com.training.weather.core.model.Coordinates;
import com.training.weather.core.model.WeatherForecast;
import com.training.weather.ingestor.core.model.City;
import com.training.weather.ingestor.core.repository.WeatherForecastDataSource;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({WeatherForecast.class})
public class WeatherForecastCachingFacadeTest {
  private static final String CITY_NAME = "TEST NAME";
  private static final String CITY_COUNTRY_NAME = "TEST COUNTRY NAME";
  private static final double LATITUDE = 10.123;
  private static final double LONGITUDE = 10.111;

  private City city;

  private List<City> cities;

  private List<WeatherForecast> weatherForecasts;

  @Mock
  private WeatherForecast weatherForecast;

  @Mock
  private WeatherForecastDataSource weatherForecastDataSource;

  @Mock
  private WeatherForecastProcessor weatherForecastProcessor;

  @Mock
  private IngestionSource ingestionSource;

  @InjectMocks
  private WeatherForecastCachingFacade weatherForecastCachingFacade;

  @Before
  public void setUp() {
    prepareTestData();
  }

  @Test
  public void refreshShouldRefreshSuccessfully() {
    when(ingestionSource.get()).thenReturn(cities);
    when(weatherForecastDataSource.getForecasts(city)).thenReturn(weatherForecasts);

    weatherForecastCachingFacade.refresh();

    verify(ingestionSource, times(1)).get();
    verify(weatherForecastDataSource, times(1)).getForecasts(city);
    verify(weatherForecastProcessor, times(1)).process(weatherForecast);
  }

  @Ignore
  private void prepareTestData() {
    prepareCity();
    prepareCities();
    prepareWeatherForecasts();
  }

  @Ignore
  private void prepareCities() {
    cities = new ArrayList<>();
    cities.add(city);
  }

  @Ignore
  private void prepareCity() {
    city = new City();

    Coordinates coordinates = new Coordinates();

    coordinates.setLatitude(LATITUDE);
    coordinates.setLongitude(LONGITUDE);

    city.setCoordinates(coordinates);
    city.setCountry(CITY_COUNTRY_NAME);
    city.setName(CITY_NAME);
  }

  @Ignore
  private void prepareWeatherForecasts() {
    weatherForecasts = new ArrayList<>();

    weatherForecasts.add(weatherForecast);
  }
}
