
package com.training.weather.ingestor.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "temp",
        "temp_min",
        "temp_max",
        "pressure",
        "sea_level",
        "grnd_level",
        "humidity",
        "temp_kf"})
public class MainParameters {

  @JsonProperty("temp")
  private Double temperature;
  @JsonProperty("temp_min")
  private Double minTemperature;
  @JsonProperty("temp_max")
  private Double maxTemperature;
  @JsonProperty("pressure")
  private Double pressure;
  @JsonProperty("sea_level")
  private Double seaLevelPressure;
  @JsonProperty("grnd_level")
  private Double groundLevelPressure;
  @JsonProperty("humidity")
  private Integer humidity;
  @JsonProperty("temp_kf")
  private Integer tempKf;

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
   * @param tempKf Integer
   */
  public MainParameters(Double temperature,
                        Double minTemperature,
                        Double maxTemperature,
                        Double pressure,
                        Double seaLevelPressure,
                        Double groundLevelPressure,
                        Integer humidity,
                        Integer tempKf) {
    this.temperature = temperature;
    this.minTemperature = minTemperature;
    this.maxTemperature = maxTemperature;
    this.pressure = pressure;
    this.seaLevelPressure = seaLevelPressure;
    this.groundLevelPressure = groundLevelPressure;
    this.humidity = humidity;
    this.tempKf = tempKf;
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

  public Integer getTempKf() {
    return tempKf;
  }

  public void setTempKf(Integer tempKf) {
    this.tempKf = tempKf;
  }
}
