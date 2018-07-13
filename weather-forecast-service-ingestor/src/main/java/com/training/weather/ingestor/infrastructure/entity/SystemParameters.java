
package com.training.weather.ingestor.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "pod"})
public class SystemParameters {

  @JsonProperty("pod")
  private String pod;

  public SystemParameters() {
  }

  public SystemParameters(String pod) {
    this.pod = pod;
  }

  public String getPod() {
    return pod;
  }

  public void setPod(String pod) {
    this.pod = pod;
  }
}
