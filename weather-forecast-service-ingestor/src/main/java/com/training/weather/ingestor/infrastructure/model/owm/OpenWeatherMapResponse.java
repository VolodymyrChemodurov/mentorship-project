package com.training.weather.ingestor.infrastructure.model.owm;

import java.util.List;

public class OpenWeatherMapResponse {

  private List<Forecast> forecasts;

  public List<Forecast> getForecasts() {
    return forecasts;
  }

  public void setForecasts(List<Forecast> forecasts) {
    this.forecasts = forecasts;
  }

}
