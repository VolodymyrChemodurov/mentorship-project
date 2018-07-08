
package com.training.weather.ingestor.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
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
public class Main {

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
}
