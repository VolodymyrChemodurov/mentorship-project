package com.training.weather.api.infrastructure.web.exception;

public class ServiceException extends RuntimeException {
  private Error errorCode;

  public ServiceException(Error errorCode) {
    super();
    this.errorCode = errorCode;
  }

  public Error getErrorCode() {
    return errorCode;
  }
}
