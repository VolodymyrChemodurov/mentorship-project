package com.training.weather.api.core.service;

import com.training.weather.api.core.repository.WeatherForecastRepository;
import com.training.weather.core.model.Coordinates;
import com.training.weather.core.model.WeatherForecast;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(LocalDateTime.class)
public class WeatherForecastServiceImplTest {

  private static final int LESS = -1;
  private static final int GREATER = 1;

  private WeatherForecast oldWeatherForecast;

  private WeatherForecast recentWeatherForecast;

  private Set<WeatherForecast> weatherForecasts;

  @Mock
  private LocalDateTime oldDateTime;

  @Mock
  private LocalDateTime recentDateTime;

  @Mock
  private LocalDateTime created;

  @Mock
  private Coordinates coordinates;

  @Mock
  private WeatherForecastRepository weatherForecastRepository;

  @InjectMocks
  private WeatherForecastServiceImpl weatherForecastService;

  @Before
  public void setUp() {
    prepareTestData();
  }

  @Test
  public void shouldReturnTheMostRecentForecast() {
    when(weatherForecastRepository.getForecastsByCoordinates(coordinates, created)).thenReturn(weatherForecasts);

    when(recentDateTime.compareTo(oldDateTime)).thenReturn(GREATER);
    when(oldDateTime.compareTo(recentDateTime)).thenReturn(LESS);

    Optional<WeatherForecast> result = weatherForecastService.getWeatherForecastByCoordinates(coordinates, created);

    assertThat(recentWeatherForecast).isEqualToComparingFieldByField(result.get());
  }

  private void prepareTestData() {
    prepareWeatherForecasts();
    prepareWeatherForecastsSet();
  }

  private void prepareWeatherForecasts() {
    oldWeatherForecast = new WeatherForecast();

    oldWeatherForecast.setCreated(oldDateTime);

    recentWeatherForecast = new WeatherForecast();

    recentWeatherForecast.setCreated(recentDateTime);
  }

  private void prepareWeatherForecastsSet() {
    weatherForecasts = new HashSet<>();

    weatherForecasts.add(recentWeatherForecast);
    weatherForecasts.add(oldWeatherForecast);
  }
}
