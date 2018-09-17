package com.training.weather.ingestor.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.weather.ingestor.core.repository.CityRepository;
import com.training.weather.ingestor.core.repository.WeatherForecastDataSource;
import com.training.weather.ingestor.core.repository.WeatherForecastRepository;
import com.training.weather.ingestor.core.service.BatchedIngestionSource;
import com.training.weather.ingestor.core.service.IngestionSource;
import com.training.weather.ingestor.core.service.WeatherForecastCachingFacade;
import com.training.weather.ingestor.core.service.WeatherForecastProcessor;
import com.training.weather.ingestor.infrastructure.repository.CityResourceRepository;
import com.training.weather.ingestor.infrastructure.resources.ResourceLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
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
   * WeatherForecastCachingFacade Bean.
   *
   * @param weatherForecastDataSource WeatherDataSource.
   * @param weatherForecastProcessor  WeatherForecastProcessor.
   * @param ingestionSource           IngestionSource.
   * @return WeatherForecastCachingFacade.
   */
  @Bean
  public WeatherForecastCachingFacade weatherForecastCachingFacade(
          WeatherForecastDataSource weatherForecastDataSource,
          WeatherForecastProcessor weatherForecastProcessor,
          IngestionSource ingestionSource) {
    return new WeatherForecastCachingFacade(
            weatherForecastDataSource,
            weatherForecastProcessor,
            ingestionSource);
  }

  @Bean
  public IngestionSource ingestionSource(CityRepository cityRepository) {
    return new BatchedIngestionSource(60, cityRepository.getAll());
  }
}
