package com.training.weather.ingestor.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.weather.ingestor.core.model.owm.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

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

  /**
   * Defines list of available cities.
   *
   * @param filename     String
   * @param objectMapper ObjectMapper
   * @return List&ltCity&gt
   * @throws IOException error while reading cities from file.
   */
  @Bean
  public List<City> cities(@Value("${spring.city.json.filename}") String filename,
                           ObjectMapper objectMapper) throws IOException {
    InputStream inputStream = getClass().getResourceAsStream(filename);

    City[] cities = objectMapper.readValue(inputStream, City[].class);

    return Arrays.asList(cities);
  }
}
