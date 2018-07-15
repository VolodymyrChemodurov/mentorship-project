package com.training.weather.ingestor.infrastructure.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.weather.ingestor.core.model.*;
import com.training.weather.ingestor.infrastructure.entity.redis.WeatherForecast;
import com.training.weather.ingestor.infrastructure.util.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring configuration and Bean definition.
 *
 * @author Oleg_Hudyma
 */
@Configuration
@ComponentScan(basePackages = "com.training.weather.ingestor.infrastructure")
@EnableScheduling
@EnableRedisRepositories
public class ApplicationConfig {

  /**
   * ObjectMapper for deserializing json.
   *
   * @return {@link ObjectMapper}
   */
  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();

    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    objectMapper.addMixIn(Coordinates.class, CoordinatesMixIn.class);
    objectMapper.addMixIn(City.class, CityMixIn.class);
    objectMapper.addMixIn(Clouds.class, CloudsMixIn.class);
    objectMapper.addMixIn(Wind.class, WindMixIn.class);
    objectMapper.addMixIn(Rain.class, RainMixIn.class);
    objectMapper.addMixIn(Snow.class, SnowMixIn.class);
    objectMapper.addMixIn(MainParameters.class, MainParametersMixIn.class);
    objectMapper.addMixIn(Forecast.class, ForecastMixIn.class);
    objectMapper.addMixIn(OpenWeatherMapResponse.class, OpenWeatherMapResponseMixIn.class);

    return objectMapper;
  }

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
   * @param mappingJackson2HttpMessageConverter MappingJackson2HttpMessageConverter
   * @return {@link RestTemplate}
   */
  @Bean
  public RestTemplate restTemplate(
          MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter
  ) {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getMessageConverters().removeIf(m -> m.getClass().getName().equals(MappingJackson2HttpMessageConverter.class.getName()));
    restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

    return restTemplate;
  }

  /**
   * Defines list of available cities.
   * @return {@link List&ltCity&gt}
   */
  @Bean
  public List<City> cities() {
    List<City> cities = new ArrayList<>();
    cities.add(new City("Lviv", new Coordinates(49.838261, 24.023239), "UA"));
    cities.add(new City("Mykolayiv", new Coordinates(49.52372, 23.98522), "UA"));
    return cities;
  }

  @Bean
  public RedisConnectionFactory connectionFactory() {
    return new LettuceConnectionFactory();
  }

  /**
   * Definition of RedisTemplate.
   */
  @Bean
  public RedisTemplate<String, WeatherForecast> redisTemplate(
          RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, WeatherForecast> redisTemplate = new RedisTemplate<>();
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashValueSerializer(new GenericToStringSerializer<>(WeatherForecast.class));
    redisTemplate.setValueSerializer(new GenericToStringSerializer<>(WeatherForecast.class));
    redisTemplate.setConnectionFactory(connectionFactory);
    return redisTemplate;
  }
}
