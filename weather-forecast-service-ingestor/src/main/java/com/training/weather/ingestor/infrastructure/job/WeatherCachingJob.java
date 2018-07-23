package com.training.weather.ingestor.infrastructure.job;

import com.training.weather.ingestor.infrastructure.service.WeatherCachingFacade;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherCachingJob {
  private static final Logger LOG = Logger.getLogger(WeatherCachingJob.class);

  private final WeatherCachingFacade weatherCachingFacade;

  public WeatherCachingJob(WeatherCachingFacade weatherCachingFacade) {
    this.weatherCachingFacade = weatherCachingFacade;
  }

  /**
   * Scheduled caching job.
   */
  @Scheduled(cron = "0 * * * * *")
  public void cacheWeatherForecast() {
    weatherCachingFacade.cache();
    LOG.info("Job finished.");
  }
}
