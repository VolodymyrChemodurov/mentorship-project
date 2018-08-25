package com.training.weather.ingestor.infrastructure.job;

import com.training.weather.core.service.WeatherForecastCachingFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherCachingJob {
  private static final Logger LOG = LoggerFactory.getLogger(WeatherCachingJob.class);

  private final WeatherForecastCachingFacade weatherForecastCachingFacade;

  public WeatherCachingJob(WeatherForecastCachingFacade weatherForecastCachingFacade) {
    this.weatherForecastCachingFacade = weatherForecastCachingFacade;
  }

  /**
   * Scheduled caching job.
   */
  @Scheduled(cron = "0 * * * * *")
  public void cacheWeatherForecast() {
    LOG.info("Caching job started.");
    weatherForecastCachingFacade.refresh();
    LOG.info("Caching job finished.");
  }
}
