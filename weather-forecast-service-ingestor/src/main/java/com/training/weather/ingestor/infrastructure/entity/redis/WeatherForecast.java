package com.training.weather.ingestor.infrastructure.entity.redis;

import com.training.weather.ingestor.infrastructure.entity.Forecast;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.GeoIndexed;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RedisHash("WeatherForecast")
public class WeatherForecast implements Serializable {
  @Id
  private String id;
  private Forecast forecast;
  @Indexed
  private String city;
  @GeoIndexed
  private Point point;
}
