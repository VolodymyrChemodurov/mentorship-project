package com.training.weather.ingestor.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.weather.ingestor.core.repository.CityRepository;
import com.training.weather.ingestor.core.repository.WeatherForecastDataSource;
import com.training.weather.ingestor.core.repository.WeatherForecastRepository;
import com.training.weather.ingestor.core.service.AsyncWeatherForecastCachingFacade;
import com.training.weather.ingestor.core.service.BatchedIngestionSource;
import com.training.weather.ingestor.core.service.IngestionSource;
import com.training.weather.ingestor.core.service.SyncWeatherForecastCachingFacade;
import com.training.weather.ingestor.core.service.WeatherForecastCachingFacade;
import com.training.weather.ingestor.core.service.WeatherForecastProcessor;
import com.training.weather.ingestor.infrastructure.repository.CityResourceRepository;
import com.training.weather.ingestor.infrastructure.resources.ResourceLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring configuration and Bean definition.
 *
 * @author Oleg_Hudyma
 */
@Configuration
@ComponentScan(basePackages = "com.training.weather.ingestor.infrastructure")
@Import({RedisConfig.class, MappingConfig.class, DataSourceConfig.class})
@EnableScheduling
public class ApplicationConfig {

  @Bean
  public ResourceLoader resourceLoader(ObjectMapper mapper) {
    return new ResourceLoader(mapper);
  }

  @Bean
  public CityRepository cityResourceRepository(
          @Value("${spring.city.json.filename}") String filename,
          ResourceLoader resourceLoader) {
    return new CityResourceRepository(filename, resourceLoader);
  }

  @Bean
  public WeatherForecastProcessor weatherForecastProcessor(
          WeatherForecastRepository repository) {
    return new WeatherForecastProcessor(repository);
  }

  /**
   * SyncWeatherForecastCachingFacade Bean.
   *
   * @param weatherForecastDataSource WeatherDataSource.
   * @param weatherForecastProcessor  WeatherForecastProcessor.
   * @param ingestionSource           IngestionSource.
   * @return SyncWeatherForecastCachingFacade.
   */
  @Bean
  @Primary
  public WeatherForecastCachingFacade syncWeatherForecastCachingFacade(
          WeatherForecastDataSource weatherForecastDataSource,
          WeatherForecastProcessor weatherForecastProcessor,
          IngestionSource ingestionSource) {
    return new SyncWeatherForecastCachingFacade(
            weatherForecastDataSource,
            weatherForecastProcessor,
            ingestionSource);
  }

  /**
   * AsyncWeatherForecastCachingFacade Bean
   *
   * @param weatherForecastDataSource WeatherForecastDataSource.
   * @param weatherForecastProcessor  WeatherForecastProcessor.
   * @param cityRepository            CityRepository.
   * @param maxRequestsPerMinute      long.
   * @param cacheRefreshFrequency     long.
   * @return WeatherForecastCachingFacade.
   */
  @Bean
  public WeatherForecastCachingFacade asyncWeatherForecastCachingFacade(
          WeatherForecastDataSource weatherForecastDataSource,
          WeatherForecastProcessor weatherForecastProcessor,
          CityRepository cityRepository,
          @Value("${owm.minute.max.requests.number}") long maxRequestsPerMinute,
          @Value("${cache.refresh.frequency.time}") long cacheRefreshFrequency) {
    return new AsyncWeatherForecastCachingFacade(
            weatherForecastDataSource,
            weatherForecastProcessor,
            cityRepository,
            maxRequestsPerMinute,
            cacheRefreshFrequency);
  }

  @Bean
  public IngestionSource ingestionSource(CityRepository cityRepository) {
    return new BatchedIngestionSource(60, cityRepository.getAll());
  }
}
