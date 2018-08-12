package com.training.weather.ingestor.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.weather.ingestor.core.repository.CityRepository;
import com.training.weather.ingestor.core.repository.GeoIndexedKeyValueRepository;
import com.training.weather.ingestor.core.service.WeatherDataSource;
import com.training.weather.ingestor.core.service.WeatherForecastCachingFacade;
import com.training.weather.ingestor.core.service.WeatherForecastForecastCachingFacadeImpl;
import com.training.weather.ingestor.core.service.WeatherForecastProcessor;
import com.training.weather.ingestor.core.service.WeatherForecastProcessorImpl;
import com.training.weather.ingestor.infrastructure.repository.CityResourceRepository;
import com.training.weather.ingestor.infrastructure.resources.ResourceLoader;
import org.springframework.beans.factory.annotation.Qualifier;
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
          @Qualifier("WeatherForecastRedisRepository") GeoIndexedKeyValueRepository repository) {
    return new WeatherForecastProcessorImpl(repository);
  }

  /**
   * WeatherForecastCachingFacade Bean.
   * @param weatherDataSource WeatherDataSource.
   * @param weatherForecastProcessor WeatherForecastProcessor.
   * @param cityRepository CityRepository.
   * @return WeatherForecastCachingFacade.
   */
  @Bean
  public WeatherForecastCachingFacade weatherForecastCachingFacade(
          @Qualifier("OpenWeatherMapDataSource") WeatherDataSource weatherDataSource,
          WeatherForecastProcessor weatherForecastProcessor,
          CityRepository cityRepository
  ) {
    return new WeatherForecastForecastCachingFacadeImpl(weatherDataSource,
            weatherForecastProcessor,
            cityRepository);
  }
}
