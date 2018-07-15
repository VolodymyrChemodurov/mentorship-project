package com.training.weather.ingestor.infrastructure.job;

import com.training.weather.ingestor.infrastructure.service.WeatherForecastCachingFacade;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OpenWeatherCachingJob {
  private static final Logger LOG = Logger.getLogger(OpenWeatherCachingJob.class);

  private final WeatherForecastCachingFacade weatherForecastCachingFacade;

  public OpenWeatherCachingJob(WeatherForecastCachingFacade weatherForecastCachingFacade) {
    this.weatherForecastCachingFacade = weatherForecastCachingFacade;
  }

  /**
   * Scheduled caching job.
   */
  @Scheduled(cron = "0 * * * * *")
  public void cacheWeatherForecast() {
    weatherForecastCachingFacade.cacheWeatherForecasts();
    LOG.info("Job finished.");
  }
}
