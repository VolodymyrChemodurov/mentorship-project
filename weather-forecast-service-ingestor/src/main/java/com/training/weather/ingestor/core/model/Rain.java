
package com.training.weather.ingestor.core.model;

import java.io.Serializable;

public class Rain implements Serializable {

  private double volume;

  public double getVolume() {
    return volume;
  }

  public void setVolume(double volume) {
    this.volume = volume;
  }

  @Override
  public String toString() {
    return "Rain{"
            + "volume=" + volume
            + '}';
  }
}
