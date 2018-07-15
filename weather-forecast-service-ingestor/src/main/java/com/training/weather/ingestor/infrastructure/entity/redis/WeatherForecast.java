package com.training.weather.ingestor.infrastructure.entity.redis;

import com.training.weather.ingestor.core.model.Forecast;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.GeoIndexed;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@RedisHash("WeatherForecast")
public class WeatherForecast implements Serializable {
  @Id
  private String id;
  private Forecast forecast;
  @Indexed
  private String city;
  @GeoIndexed
  private Point point;

  /**
   * Creates instance of {@link WeatherForecast}.
   */
  public WeatherForecast() {
    //Default Constructor.
  }

  /**
   * Constructor.
   *
   * @param forecast Forecast
   * @param city     String
   * @param point    Point
   */
  public WeatherForecast(Forecast forecast, String city, Point point) {
    this.forecast = forecast;
    this.city = city;
    this.point = point;
  }

  /**
   * Constructor.
   *
   * @param id       String
   * @param forecast Forecast
   * @param city     String
   * @param point    Point
   */
  public WeatherForecast(String id, Forecast forecast, String city, Point point) {
    this.id = id;
    this.forecast = forecast;
    this.city = city;
    this.point = point;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Forecast getForecast() {
    return forecast;
  }

  public void setForecast(Forecast forecast) {
    this.forecast = forecast;
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
