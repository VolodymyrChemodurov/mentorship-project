
package com.training.weather.ingestor.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
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
}
