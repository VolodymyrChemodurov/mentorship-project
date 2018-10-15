package com.training.weather.api.infrastructure.web.exception;

import org.springframework.http.HttpStatus;

public enum Error {
  FORECAST_NOT_FOUND("10.FRCST", "Weather forecast does not exists.", HttpStatus.NOT_FOUND),
  INTERNAL_ERROR("49.FRCST", "Internal servere error.", HttpStatus.INTERNAL_SERVER_ERROR);

  private ApiError error;

  Error(String code, String message, HttpStatus httpStatus) {
    error = new ApiError(code, message, httpStatus);
  }

  public ApiError getError() {
    return error;
  }
}
