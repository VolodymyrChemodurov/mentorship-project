package com.training.weather.ingestor.job;

import com.training.weather.ingestor.core.service.WeatherForecastCachingFacade;
import com.training.weather.ingestor.infrastructure.job.WeatherCachingJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WeatherCachingJobTest {

  @Mock
  private WeatherForecastCachingFacade weatherForecastCachingFacade;

  @InjectMocks
  private WeatherCachingJob weatherCachingJob;

  @Test
  public void cacheWeatherForecastShouldSuccess(){
    doNothing().when(weatherForecastCachingFacade).refresh();

    weatherCachingJob.cacheWeatherForecast();

    verify(weatherForecastCachingFacade, times(1)).refresh();
  }
}
