package com.training.weather.ingestor.infrastructure.job;

import com.training.weather.ingestor.core.service.WeatherCachingFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherCachingJob {
  private static final Logger LOG = LoggerFactory.getLogger(WeatherCachingJob.class);

  private final WeatherCachingFacade weatherCachingFacade;

  public WeatherCachingJob(WeatherCachingFacade weatherCachingFacade) {
    this.weatherCachingFacade = weatherCachingFacade;
  }

  /**
   * Scheduled caching job.
   */
  @Scheduled(cron = "0 * * * * *")
  public void cacheWeatherForecast() {
    LOG.info("Caching job started.");
    weatherCachingFacade.refresh();
    LOG.info("Caching job finished.");
  }
}
