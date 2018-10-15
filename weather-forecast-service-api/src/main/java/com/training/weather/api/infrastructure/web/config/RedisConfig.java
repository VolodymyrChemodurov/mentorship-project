package com.training.weather.api.infrastructure.web.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.codec.ByteArrayCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

  @Bean
  public StatefulRedisConnection<byte[], byte[]> statefulRedisConnection(
          @Value("${redis.connection.string}") String connectionString) {
    return RedisClient.create(connectionString).connect(new ByteArrayCodec());
  }
}
