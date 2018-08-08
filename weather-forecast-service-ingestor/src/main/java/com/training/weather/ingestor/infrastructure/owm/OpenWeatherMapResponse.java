package com.training.weather.ingestor.infrastructure.owm;

import java.util.List;

public class OpenWeatherMapResponse {

  private List<OpenWeatherMapForecast> forecasts;

  public List<OpenWeatherMapForecast> getForecasts() {
    return forecasts;
  }

  public void setForecasts(List<OpenWeatherMapForecast> forecasts) {
    this.forecasts = forecasts;
  }
}
