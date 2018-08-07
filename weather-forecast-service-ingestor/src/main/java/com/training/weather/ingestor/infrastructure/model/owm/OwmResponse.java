package com.training.weather.ingestor.infrastructure.model.owm;

import com.training.weather.ingestor.core.model.WeatherForecastProviderResponse;
import com.training.weather.ingestor.core.model.owm.Coordinates;
import com.training.weather.ingestor.core.model.owm.Forecast;

import java.util.List;

public class OwmResponse implements WeatherForecastProviderResponse {

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
