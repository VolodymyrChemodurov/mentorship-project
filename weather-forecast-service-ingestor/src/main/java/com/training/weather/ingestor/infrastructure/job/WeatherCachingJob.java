package com.training.weather.ingestor.infrastructure.job;

import com.training.weather.ingestor.core.service.IngestionSource;
import com.training.weather.ingestor.core.service.WeatherForecastCachingFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherCachingJob {
  private static final Logger LOG = LoggerFactory.getLogger(WeatherCachingJob.class);

  private final WeatherForecastCachingFacade weatherForecastCachingFacade;

  private final IngestionSource ingestionSource;

  public WeatherCachingJob(
      WeatherForecastCachingFacade weatherForecastCachingFacade,
      IngestionSource ingestionSource) {
    this.weatherForecastCachingFacade = weatherForecastCachingFacade;
    this.ingestionSource = ingestionSource;
  }

  /**
   * Scheduled caching job.
   */
  @Scheduled(fixedDelay = 60000)
  public void cacheWeatherForecast() {
    LOG.info("Caching job started.");
    weatherForecastCachingFacade.refresh();
    LOG.info("Caching job finished.");
  }

  /**
   * Resets ingestion source every two minutes.
   */
  @Scheduled(fixedDelay = 120000, initialDelay = 120000)
  public void resetIngestionSource() {
    ingestionSource.reset();
    LOG.info("Ingestion source was reset.");
  }
}
