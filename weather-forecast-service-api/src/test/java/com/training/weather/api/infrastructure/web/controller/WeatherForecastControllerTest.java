package com.training.weather.api.infrastructure.web.controller;

import com.training.weather.api.core.service.WeatherForecastFacade;
import com.training.weather.api.infrastructure.web.controller.api.v1.rest.WeatherForecastController;
import com.training.weather.core.model.WeatherForecast;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({WeatherForecast.class, LocalDateTime.class})
public class WeatherForecastControllerTest {

  private static final double LONGITUDE = 0.00;
  private static final double LATITUDE = 0.00;

  @Mock
  private LocalDateTime dateTime;

  @Mock
  private WeatherForecast weatherForecast;

  @Mock
  private WeatherForecastFacade weatherForecastFacade;

  @InjectMocks
  private WeatherForecastController weatherForecastController;

  @Test
  public void shouldReturnWeatherForecastSuccessfully() {
    when(weatherForecastFacade.getWeatherForecastByCoordinates(LONGITUDE, LATITUDE, dateTime)).
            thenReturn(weatherForecast);

    ResponseEntity<WeatherForecast> result = weatherForecastController.getWeatherForecastByCoordinates(LONGITUDE,
            LATITUDE,
            dateTime);

    assertEquals(weatherForecast, result.getBody());
  }
}
