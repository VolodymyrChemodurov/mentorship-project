package com.training.weather.ingestor.infrastructure.entity.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.index.GeoIndexed;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class WeatherForecastKey implements Serializable {
  @Id @Indexed
  private String city;
  @GeoIndexed
  private Point point;
}