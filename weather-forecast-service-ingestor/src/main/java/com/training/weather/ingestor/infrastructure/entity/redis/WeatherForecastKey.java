package com.training.weather.ingestor.infrastructure.entity.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.index.GeoIndexed;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

public class WeatherForecastKey implements Serializable {
  @Id @Indexed
  private String city;
  @GeoIndexed
  private Point point;

  public WeatherForecastKey() {
  }

  public WeatherForecastKey(String city, Point point) {
    this.city = city;
    this.point = point;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Point getPoint() {
    return point;
  }

  public void setPoint(Point point) {
    this.point = point;
  }
}