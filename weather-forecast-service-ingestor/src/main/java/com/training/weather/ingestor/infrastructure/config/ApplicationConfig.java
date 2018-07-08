package com.training.weather.ingestor.infrastructure.config;

import com.training.weather.ingestor.infrastructure.entity.City;
import com.training.weather.ingestor.infrastructure.entity.Coordinates;
import com.training.weather.ingestor.infrastructure.entity.redis.WeatherForecast;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring configuration and Bean definition.
 * @author Oleg_Hudyma
 */
@Configuration
@ComponentScan(basePackages = "com.training.weather.ingestor.infrastructure")
@EnableScheduling
@EnableRedisRepositories
public class ApplicationConfig {
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  /**
   * Definition of available cities.
   */
  @Bean
  public List<City> cities() {
    List<City> cities = new ArrayList<>();
    cities.add(new City(null, "Lviv", new Coordinates(49.838261, 24.023239), "UA"));
    cities.add(new City(null, "Mykolayiv", new Coordinates(49.52372,23.98522), "UA"));
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
    redisTemplate.setKeySerializer( new StringRedisSerializer() );
    redisTemplate.setHashValueSerializer( new GenericToStringSerializer<>(WeatherForecast.class ) );
    redisTemplate.setValueSerializer(new GenericToStringSerializer<>(WeatherForecast.class));
    redisTemplate.setConnectionFactory(connectionFactory);
    return redisTemplate;
  }
}
