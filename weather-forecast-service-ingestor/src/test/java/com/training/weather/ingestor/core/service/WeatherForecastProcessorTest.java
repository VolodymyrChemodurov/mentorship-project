package com.training.weather.ingestor.core.service;

import com.training.weather.core.model.WeatherForecast;
import com.training.weather.ingestor.core.repository.WeatherForecastRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WeatherForecastProcessorTest {

  private WeatherForecast weatherForecast;

  @Mock
  private WeatherForecastRepository weatherForecastRepository;

  @InjectMocks
  private WeatherForecastProcessor weatherForecastProcessor;

  @Before
  public void setUp() {
    weatherForecast = new WeatherForecast();
  }

  @Test
  public void shouldProcess() {
    weatherForecastProcessor.process(weatherForecast);

    verify(weatherForecastRepository, times(1)).save(eq(weatherForecast));
  }

  @Test(expected = Exception.class)
  public void shouldRethrowException() {
    doThrow(Exception.class).when(weatherForecastRepository).save(weatherForecast);

    weatherForecastProcessor.process(weatherForecast);
  }
}
