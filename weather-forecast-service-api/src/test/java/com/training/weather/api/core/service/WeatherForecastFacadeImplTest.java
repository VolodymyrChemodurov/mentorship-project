package com.training.weather.api.core.service;

import com.training.weather.api.infrastructure.web.exception.ServiceException;
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
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({WeatherForecast.class, Coordinates.class, Optional.class})
public class WeatherForecastFacadeImplTest {

  private static final double LONGITUDE = 0.00;
  private static final double LATITUDE = 0.00;

  @Mock
  private LocalDateTime dateTime;

  @Mock
  private Coordinates coordinates;

  @Mock
  private WeatherForecast weatherForecast;

  @Mock
  private WeatherForecastService weatherForecastService;

  @InjectMocks
  private WeatherForecastFacadeImpl weatherForecastFacade;

  @Test
  public void shouldReturnWeatherForecastIfExists() throws Exception {
    whenNew(Coordinates.class).withNoArguments().thenReturn(coordinates);

    when(weatherForecastService.getWeatherForecastByCoordinates(any(), any()))
            .thenReturn(Optional.of(weatherForecast));

    WeatherForecast result = weatherForecastFacade.getWeatherForecastByCoordinates(LONGITUDE, LATITUDE, dateTime);

    assertEquals(weatherForecast, result);
  }

  @Test(expected = ServiceException.class)
  public void shouldThrowServiceExceptionIfWeatherForecastDoesNotExists() throws Exception {
    whenNew(Coordinates.class).withNoArguments().thenReturn(coordinates);

    when(weatherForecastService.getWeatherForecastByCoordinates(any(), any()))
            .thenReturn(Optional.empty());

    weatherForecastFacade.getWeatherForecastByCoordinates(LONGITUDE, LATITUDE, dateTime);
  }
}
