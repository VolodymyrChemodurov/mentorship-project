package com.training.weather.ingestor.core.model;

import java.io.Serializable;

public class MainParameters implements Serializable {

  private Double temperature;
  private Double minTemperature;
  private Double maxTemperature;
  private Double pressure;
  private Double seaLevelPressure;
  private Double groundLevelPressure;
  private Integer humidity;

  /**
   * Creates instance of {@link MainParameters}.
   */
  public MainParameters() {
    //Default Constructor.
  }

  /**
   * Constructor.
   * @param temperature Double
   * @param minTemperature Double
   * @param maxTemperature Double
   * @param pressure Double
   * @param seaLevelPressure Double
   * @param groundLevelPressure Double
   * @param humidity Integer
   */
  public MainParameters(Double temperature,
                        Double minTemperature,
                        Double maxTemperature,
                        Double pressure,
                        Double seaLevelPressure,
                        Double groundLevelPressure,
                        Integer humidity) {
    this.temperature = temperature;
    this.minTemperature = minTemperature;
    this.maxTemperature = maxTemperature;
    this.pressure = pressure;
    this.seaLevelPressure = seaLevelPressure;
    this.groundLevelPressure = groundLevelPressure;
    this.humidity = humidity;
  }

  public Double getTemperature() {
    return temperature;
  }

  public void setTemperature(Double temperature) {
    this.temperature = temperature;
  }

  public Double getMinTemperature() {
    return minTemperature;
  }

  public void setMinTemperature(Double minTemperature) {
    this.minTemperature = minTemperature;
  }

  public Double getMaxTemperature() {
    return maxTemperature;
  }

  public void setMaxTemperature(Double maxTemperature) {
    this.maxTemperature = maxTemperature;
  }

  public Double getPressure() {
    return pressure;
  }

  public void setPressure(Double pressure) {
    this.pressure = pressure;
  }

  public Double getSeaLevelPressure() {
    return seaLevelPressure;
  }

  public void setSeaLevelPressure(Double seaLevelPressure) {
    this.seaLevelPressure = seaLevelPressure;
  }

  public Double getGroundLevelPressure() {
    return groundLevelPressure;
  }

  public void setGroundLevelPressure(Double groundLevelPressure) {
    this.groundLevelPressure = groundLevelPressure;
  }

  public Integer getHumidity() {
    return humidity;
  }

  public void setHumidity(Integer humidity) {
    this.humidity = humidity;
  }

  @Override
  public String toString() {
    return "MainParameters{" +
            "temperature=" + temperature +
            ", minTemperature=" + minTemperature +
            ", maxTemperature=" + maxTemperature +
            ", pressure=" + pressure +
            ", seaLevelPressure=" + seaLevelPressure +
            ", groundLevelPressure=" + groundLevelPressure +
            ", humidity=" + humidity +
            '}';
  }
}
