package com.training.weather.api.infrastructure.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.weather.api.core.repository.WeatherForecastRepository;
import com.training.weather.api.core.service.WeatherForecastFacade;
import com.training.weather.api.core.service.WeatherForecastFacadeImpl;
import com.training.weather.api.core.service.WeatherForecastService;
import com.training.weather.api.core.service.WeatherForecastServiceImpl;
import com.training.weather.api.infrastructure.web.repository.WeatherForecastRedisRepository;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Spring configuration and Bean definition.
 *
 * @author Oleg_Hudyma
 */
@Configuration
@ComponentScan(basePackages = "com.training.weather.api.infrastructure.web")
@Import({RedisConfig.class, MappingConfig.class})
@EnableWebMvc
public class ApplicationConfig {

  @Bean
  public WeatherForecastFacade weatherForecastFacade(
          WeatherForecastService weatherForecastService) {
    return new WeatherForecastFacadeImpl(weatherForecastService);
  }

  @Bean
  public WeatherForecastService weatherForecastService(
          WeatherForecastRepository weatherForecastRepository) {
    return new WeatherForecastServiceImpl(weatherForecastRepository);
  }

  /**
   * Repository for communicating with Redis.
   *
   * @param radius       lookup radius.
   * @param connection   {@link StatefulRedisConnection}.
   * @param objectMapper {@link ObjectMapper}.
   * @return {@link WeatherForecastRepository} implementation.
   */
  @Bean
  public WeatherForecastRepository weatherForecastRepository(
          @Value("${redis.geo.radius.km}") int radius,
          StatefulRedisConnection<byte[], byte[]> connection,
          ObjectMapper objectMapper) {
    return new WeatherForecastRedisRepository(radius,
            connection,
            objectMapper);
  }
}
