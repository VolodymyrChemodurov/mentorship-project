package com.training.weather.ingestor.core.model;

import java.io.Serializable;
import java.util.List;

public class Forecast implements Serializable {
  private Integer timestamp;
  private MainParameters mainParameters;
  private List<Weather> weather;
  private Clouds clouds;
  private Wind wind;
  private Rain rain;
  private Snow snow;
  private String date;

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

  public Snow getSnow() {
    return snow;
  }

  public void setSnow(Snow snow) {
    this.snow = snow;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "Forecast{" +
            "timestamp=" + timestamp +
            ", mainParameters=" + mainParameters +
            ", weather=" + weather +
            ", clouds=" + clouds +
            ", wind=" + wind +
            ", rain=" + rain +
            ", snow=" + snow +
            ", date='" + date + '\'' +
            '}';
  }
}
