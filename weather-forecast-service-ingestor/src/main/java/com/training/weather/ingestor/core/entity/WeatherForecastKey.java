package com.training.weather.ingestor.core.entity;

import com.training.weather.ingestor.core.model.Coordinates;

import java.io.Serializable;

public class WeatherForecastKey implements Serializable {

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