package com.training.weather.ingestor.infrastructure.config;

import com.training.weather.ingestor.core.model.WeatherForecastRedisKey;
import com.training.weather.ingestor.core.model.WeatherForecastRedisValue;
import com.training.weather.ingestor.infrastructure.lettuce.ObjectCodec;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.codec.RedisCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

  @Bean
  public RedisCodec<WeatherForecastRedisKey, WeatherForecastRedisValue> redisCodec() {
    return new ObjectCodec<>();
  }

  @Bean
  public RedisClient redisClient(@Value("${redis.connection.string}") String connectionString) {
    return RedisClient.create(connectionString);
  }

  @Bean
  public StatefulRedisConnection<WeatherForecastRedisKey,
          WeatherForecastRedisValue> statefulRedisConnection(
          RedisCodec<WeatherForecastRedisKey, WeatherForecastRedisValue> redisCodec,
          RedisClient redisClient) {
    return redisClient.connect(redisCodec);
  }
}
