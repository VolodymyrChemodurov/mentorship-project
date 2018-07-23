package com.training.weather.ingestor.core.model;

import java.io.Serializable;

public class Snow implements Serializable {

  private String volume;

  public String getVolume() {
    return volume;
  }

  public void setVolume(String volume) {
    this.volume = volume;
  }
}
