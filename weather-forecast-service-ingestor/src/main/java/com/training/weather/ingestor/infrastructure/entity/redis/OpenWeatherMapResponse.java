package com.training.weather.ingestor.infrastructure.entity.redis;

import com.training.weather.ingestor.core.model.Coordinates;
import com.training.weather.ingestor.core.model.Forecast;

import java.util.List;

public class OpenWeatherMapResponse {

  private List<Forecast> forecasts;
  private Coordinates coordinates;

  public List<Forecast> getForecasts() {
    return forecasts;
  }

  public void setForecasts(List<Forecast> forecasts) {
    this.forecasts = forecasts;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }
}
