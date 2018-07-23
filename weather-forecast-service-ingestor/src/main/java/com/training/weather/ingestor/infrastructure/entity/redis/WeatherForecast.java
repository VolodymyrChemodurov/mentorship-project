package com.training.weather.ingestor.infrastructure.entity.redis;

import com.training.weather.ingestor.core.model.Forecast;

import java.io.Serializable;

public class WeatherForecast implements Serializable {

  private Forecast forecast;

  public Forecast getForecast() {
    return forecast;
  }

  public void setForecast(Forecast forecast) {
    this.forecast = forecast;
  }
}
