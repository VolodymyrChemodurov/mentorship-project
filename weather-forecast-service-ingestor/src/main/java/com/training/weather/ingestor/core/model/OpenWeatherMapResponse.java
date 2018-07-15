
package com.training.weather.ingestor.core.model;

import java.util.List;

public class OpenWeatherMapResponse {

  private String code;
  private Double message;
  private List<Forecast> forecasts;
  private City city;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Double getMessage() {
    return message;
  }

  public void setMessage(Double message) {
    this.message = message;
  }

  public List<Forecast> getForecasts() {
    return forecasts;
  }

  public void setForecasts(List<Forecast> forecasts) {
    this.forecasts = forecasts;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }
}
