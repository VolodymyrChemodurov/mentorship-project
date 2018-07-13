package com.training.weather.ingestor.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;

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
  private MainParameters mainParameters;
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

  public Forecast() {
  }

  /**
   * Constructor.
   * @param timestamp Integer
   * @param mainParameters MainParameters
   * @param weather List<Weather>
   * @param clouds Clouds
   * @param wind Wind
   * @param rain Rain
   * @param systemParameters SystemParameters
   * @param date String
   */
  public Forecast(Integer timestamp,
                  MainParameters mainParameters,
                  List<Weather> weather,
                  Clouds clouds,
                  Wind wind,
                  Rain rain,
                  SystemParameters systemParameters,
                  String date) {
    this.timestamp = timestamp;
    this.mainParameters = mainParameters;
    this.weather = weather;
    this.clouds = clouds;
    this.wind = wind;
    this.rain = rain;
    this.systemParameters = systemParameters;
    this.date = date;
  }

  public Integer getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Integer timestamp) {
    this.timestamp = timestamp;
  }

  public MainParameters getMainParameters() {
    return mainParameters;
  }

  public void setMainParameters(MainParameters mainParameters) {
    this.mainParameters = mainParameters;
  }

  public List<Weather> getWeather() {
    return weather;
  }

  public void setWeather(List<Weather> weather) {
    this.weather = weather;
  }

  public Clouds getClouds() {
    return clouds;
  }

  public void setClouds(Clouds clouds) {
    this.clouds = clouds;
  }

  public Wind getWind() {
    return wind;
  }

  public void setWind(Wind wind) {
    this.wind = wind;
  }

  public Rain getRain() {
    return rain;
  }

  public void setRain(Rain rain) {
    this.rain = rain;
  }

  public SystemParameters getSystemParameters() {
    return systemParameters;
  }

  public void setSystemParameters(SystemParameters systemParameters) {
    this.systemParameters = systemParameters;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
}
