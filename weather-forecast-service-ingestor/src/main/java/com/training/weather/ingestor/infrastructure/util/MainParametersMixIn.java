package com.training.weather.ingestor.infrastructure.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface MainParametersMixIn {
  @JsonProperty("dt")
  Integer getTimestamp();

  @JsonProperty("temp")
  Double getTemperature();

  @JsonProperty("temp_min")
  Double getMinTemperature();

  @JsonProperty("temp_max")
  Double getMaxTemperature();

  @JsonProperty("pressure")
  Double getPressure();

  @JsonProperty("sea_level")
  Double getSeaLevelPressure();

  @JsonProperty("grnd_level")
  Double getGroundLevelPressure();

  @JsonProperty("humidity")
  Integer getHumidity();
}
