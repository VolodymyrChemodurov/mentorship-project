package com.training.weather.ingestor.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "dt",
        "main",
        "weather",
        "clouds",
        "wind",
        "rain",
        "sys",
        "dt_txt"})
public class Forecast {
  @JsonProperty("dt")
  @Indexed
  private Integer timestamp;
  @JsonProperty("main")
  private Main main;
  @JsonProperty("weather")
  private List<Weather> weather;
  @JsonProperty("clouds")
  private Clouds clouds;
  @JsonProperty("wind")
  private Wind wind;
  @JsonProperty("rain")
  private Rain rain;
  @JsonProperty("sys")
  private SystemParameters systemParameters;
  @JsonProperty("dt_txt")
  private String date;
}
