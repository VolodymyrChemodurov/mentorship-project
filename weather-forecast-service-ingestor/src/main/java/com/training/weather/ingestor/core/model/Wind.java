
package com.training.weather.ingestor.core.model;

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
}
