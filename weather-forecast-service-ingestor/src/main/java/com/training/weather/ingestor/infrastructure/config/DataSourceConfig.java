package com.training.weather.ingestor.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DataSourceConfig {

  /**
   * Defines message  converter based on Object Mapper.
   *
   * @param objectMapper ObjectMapper
   * @return {@link MappingJackson2HttpMessageConverter}
   */
  @Bean
  public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(
          ObjectMapper objectMapper) {
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

    converter.setObjectMapper(objectMapper);

    return converter;
  }

  /**
   * Defines Rest Template.
   *
   * @param jacksonMessageConverter MappingJackson2HttpMessageConverter
   * @return {@link RestTemplate}
   */
  @Bean
  public RestTemplate restTemplate(MappingJackson2HttpMessageConverter jacksonMessageConverter) {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getMessageConverters().removeIf(m -> m.getClass().getName()
            .equals(MappingJackson2HttpMessageConverter.class.getName()));
    restTemplate.getMessageConverters().add(jacksonMessageConverter);

    return restTemplate;
  }
}
