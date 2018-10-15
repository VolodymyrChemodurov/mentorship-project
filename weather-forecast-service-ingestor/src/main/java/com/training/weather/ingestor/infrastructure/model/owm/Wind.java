package com.training.weather.ingestor.infrastructure.model.owm;

import java.io.Serializable;

public class Wind implements Serializable {

  private Double speed;
  private Double degree;

  /**
   * Creates instance of {@link Wind}.
   */
  public Wind() {
    //Default Constructor.
  }

  public Wind(Double speed, Double degree) {
    this.speed = speed;
    this.degree = degree;
  }

  public Double getSpeed() {
    return speed;
  }

  public void setSpeed(Double speed) {
    this.speed = speed;
  }

  public Double getDegree() {
    return degree;
  }

  public void setDegree(Double degree) {
    this.degree = degree;
  }

  @Override
  public String toString() {
    return "Wind{"
            + "speed=" + speed
            + ", degree=" + degree
            + '}';
  }
}
