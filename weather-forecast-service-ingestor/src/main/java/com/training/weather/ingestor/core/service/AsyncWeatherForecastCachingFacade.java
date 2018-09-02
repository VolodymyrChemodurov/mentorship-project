package com.training.weather.ingestor.core.service;

import com.training.weather.ingestor.core.model.City;
import com.training.weather.ingestor.core.repository.CityRepository;
import com.training.weather.ingestor.core.repository.WeatherForecastDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public class AsyncWeatherForecastCachingFacade implements WeatherForecastCachingFacade {
  private static final Logger LOG =
          LoggerFactory.getLogger(AsyncWeatherForecastCachingFacade.class);

  private static final long MINUTE = 60000;

  private boolean isJobCompleted;

  private final WeatherForecastDataSource weatherForecastDataSource;

  private final WeatherForecastProcessor weatherForecastProcessor;

  private final CityRepository cityRepository;

  private Iterator<City> cityIterator;

  private final long maxRequestsPerMinute;

  private final long cacheRefreshFrequency;

  /**
   * Constructor.
   *
   * @param weatherForecastDataSource weatherDataSource
   * @param weatherForecastProcessor  WeatherForecastProcessorImpl
   * @param cityRepository            CityResourceRepository
   */
  public AsyncWeatherForecastCachingFacade(
          WeatherForecastDataSource weatherForecastDataSource,
          WeatherForecastProcessor weatherForecastProcessor,
          CityRepository cityRepository,
          long maxRequestsPerMinute,
          long cacheRefreshFrequency) {
    this.weatherForecastDataSource = weatherForecastDataSource;
    this.weatherForecastProcessor = weatherForecastProcessor;
    this.cityRepository = cityRepository;
    this.maxRequestsPerMinute = maxRequestsPerMinute;
    this.cacheRefreshFrequency = cacheRefreshFrequency;
    this.cityIterator = cityRepository.getAll().iterator();
  }

  @Override
  public void refresh() {
    this.executeWithTimeout(() -> {
      while (!Thread.interrupted() && !isJobCompleted) {
        readChunk();
      }
      LOG.info("Job Completed.");
      cleanupJobHistory();
    }, cacheRefreshFrequency);
  }

  private void readChunk() {
    this.executeWithTimeout(() -> {
      for (int i = 0; i < maxRequestsPerMinute; i++) {
        if (Thread.interrupted()) {
          LOG.info("ReadChunk timeout.");
          break;
        }
        if (!cityIterator.hasNext()) {
          isJobCompleted = true;
          LOG.info("All cities cached.");
          break;
        }

        weatherForecastDataSource.getForecasts(cityIterator.next())
                .forEach(weatherForecastProcessor::process);
      }
    }, MINUTE);
  }

  private void cleanupJobHistory() {
    cityIterator = cityRepository.getAll().iterator();
    isJobCompleted = false;
  }

  private void executeWithTimeout(Runnable task, long timeout) {
    Thread thread = new Thread(task);

    try {
      thread.start();

      Thread.sleep(timeout);

      if (thread.isAlive()) {
        thread.interrupt();
      }
    } catch (InterruptedException e) {
      LOG.error(e.getMessage());
    }
  }
}
