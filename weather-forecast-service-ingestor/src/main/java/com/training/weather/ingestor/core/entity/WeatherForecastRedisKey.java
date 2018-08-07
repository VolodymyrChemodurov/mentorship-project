package com.training.weather.ingestor.core.entity;

import com.training.weather.ingestor.core.model.owm.Coordinates;

public class WeatherForecastRedisKey implements WeatherForecastKey {

  private int timestamp;

  private Coordinates coordinates;

  public int getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(int timestamp) {
    this.timestamp = timestamp;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }
}