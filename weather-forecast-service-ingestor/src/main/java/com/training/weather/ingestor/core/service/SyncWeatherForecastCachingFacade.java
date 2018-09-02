package com.training.weather.ingestor.core.service;

import com.training.weather.ingestor.core.model.City;
import com.training.weather.ingestor.core.repository.CityRepository;
import com.training.weather.ingestor.core.repository.WeatherForecastDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Iterator;

/**
 * Deprecated because it is synchronized.
 *
 * @deprecated Use {@link AsyncWeatherForecastCachingFacade} instead.
 */
@Deprecated
public class SyncWeatherForecastCachingFacade implements WeatherForecastCachingFacade {

  private static final Logger LOG = LoggerFactory.getLogger(SyncWeatherForecastCachingFacade.class);

  private boolean isJobCompleted;

  private final WeatherForecastDataSource weatherForecastDataSource;

  private final WeatherForecastProcessor weatherForecastProcessor;

  private final CityRepository cityRepository;

  private Iterator<City> cityIterator;

  private final long maxRequestsPerMinute;

  /**
   * Constructor.
   *
   * @param weatherForecastDataSource weatherDataSource
   * @param weatherForecastProcessor  WeatherForecastProcessorImpl
   * @param cityRepository            CityResourceRepository
   */
  public SyncWeatherForecastCachingFacade(
          WeatherForecastDataSource weatherForecastDataSource,
          WeatherForecastProcessor weatherForecastProcessor,
          CityRepository cityRepository,
          long maxRequestsPerMinute) {
    this.weatherForecastDataSource = weatherForecastDataSource;
    this.weatherForecastProcessor = weatherForecastProcessor;
    this.cityRepository = cityRepository;
    this.cityIterator = cityRepository.getAll().iterator();
    this.maxRequestsPerMinute = maxRequestsPerMinute;
  }

  /**
   * Method for retrieving weather forecasts from OpenWeather API and process them.
   */
  @Override
  public void refresh() {
    LOG.info("Refreshing cache.");
    for (int i = 0; i < maxRequestsPerMinute; i++) {
      if (!isJobCompleted) {
        if (!cityIterator.hasNext()) {
          isJobCompleted = true;
          break;
        }

        weatherForecastDataSource.getForecasts(cityIterator.next())
                .forEach(weatherForecastProcessor::process);
      }
    }
  }

  @Scheduled(cron = "0 0/5 * * * *")
  public void cleanupJobHistory() {
    cityIterator = cityRepository.getAll().iterator();
    isJobCompleted = false;
  }
}
