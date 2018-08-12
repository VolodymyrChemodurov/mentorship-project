package com.training.weather.ingestor.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.weather.ingestor.core.service.WeatherDataSource;
import com.training.weather.ingestor.core.util.Mapper;
import com.training.weather.ingestor.infrastructure.model.owm.OpenWeatherMapResponse;
import com.training.weather.ingestor.infrastructure.service.owm.OpenWeatherMapDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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

  /**
   * WeatherDataSource Bean.
   *
   * @param apiScheme    String.
   * @param apiHost      String.
   * @param apiKey       String.
   * @param mapper       Mapper.
   * @param restTemplate RestTemplate.
   * @return WeatherDataSource.
   */
  @Bean
  @Qualifier("OpenWeatherMapDataSource")
  public WeatherDataSource openWeatherMapDataSource(
          @Value("${owm.api.scheme}") String apiScheme,
          @Value("${owm.api.host}") String apiHost,
          @Value("${owm.api.key}") String apiKey,
          @Qualifier("OpenWeatherMapRedisMapper") Mapper<OpenWeatherMapResponse> mapper,
          RestTemplate restTemplate) {
    return new OpenWeatherMapDataSource(apiScheme,
            apiHost,
            apiKey,
            mapper,
            restTemplate);
  }
}
