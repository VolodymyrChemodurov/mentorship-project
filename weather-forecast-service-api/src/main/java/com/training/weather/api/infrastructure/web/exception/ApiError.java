package com.training.weather.api.infrastructure.web.exception;

import org.springframework.http.HttpStatus;

public class ApiError {
  private String code;
  private String message;
  private HttpStatus httpStatus;

  /**
   * Constructor.
   *
   * @param code       internal code.
   * @param message    message.
   * @param httpStatus {@link HttpStatus}.
   */
  public ApiError(String code, String message, HttpStatus httpStatus) {
    this.code = code;
    this.message = message;
    this.httpStatus = httpStatus;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}
