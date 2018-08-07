package com.training.weather.ingestor.job;

import com.training.weather.ingestor.core.service.WeatherCachingFacade;
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
  private WeatherCachingFacade weatherCachingFacade;

  @InjectMocks
  private WeatherCachingJob weatherCachingJob;

  @Test
  public void cacheWeatherForecastShouldSuccess(){
    doNothing().when(weatherCachingFacade).refresh();

    weatherCachingJob.cacheWeatherForecast();

    verify(weatherCachingFacade, times(1)).refresh();
  }
}
