
package com.training.weather.ingestor.core.model;

import java.io.Serializable;

public class Clouds implements Serializable {

  private Integer volume;

  public Integer getVolume() {
    return volume;
  }

  public void setVolume(Integer volume) {
    this.volume = volume;
  }

  @Override
  public String toString() {
    return "Clouds{" +
            "volume=" + volume +
            '}';
  }
}
