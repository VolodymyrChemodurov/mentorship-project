
package com.training.weather.ingestor.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cod",
        "message",
        "cnt",
        "list",
        "city"})
public class OpenWeatherForecastResponse {

  @JsonProperty("cod")
  private String code;
  @JsonProperty("message")
  private Double message;
  @JsonProperty("cnt")
  private Integer cnt;
  @JsonProperty("list")
  private List<Forecast> forecasts;
  @JsonProperty("city")
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

  public Integer getCnt() {
    return cnt;
  }

  public void setCnt(Integer cnt) {
    this.cnt = cnt;
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
