package com.training.weather.ingestor.infrastructure.job;

import com.training.weather.ingestor.core.service.WeatherForecastCachingFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WeatherCachingJobTest {
  @Mock
  private WeatherForecastCachingFacade weatherForecastCachingFacade;

  @InjectMocks
  private WeatherCachingJob weatherCachingJob;

  @Test
  public void shouldRefresh() {
    weatherCachingJob.cacheWeatherForecast();

    verify(weatherForecastCachingFacade, times(1)).refresh();
  }

  @Test(expected = Exception.class)
  public void shouldRethrowException() {
    doThrow(Exception.class).when(weatherForecastCachingFacade).refresh();

    weatherCachingJob.cacheWeatherForecast();
  }
}
