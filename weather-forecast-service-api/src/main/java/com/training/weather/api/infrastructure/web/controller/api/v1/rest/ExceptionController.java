package com.training.weather.api.infrastructure.web.controller.api.v1.rest;

import com.training.weather.api.infrastructure.web.exception.ApiError;
import com.training.weather.api.infrastructure.web.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

  /**
   * Default exception handler. Handle exception and map it into {@link ApiError}.
   *
   * @param exception {@link ServiceException}.
   * @return {@link ApiError}.
   */
  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<ApiError> defaultExceptionHandler(ServiceException exception) {
    ApiError errorCode = exception.getErrorCode().getError();

    return new ResponseEntity<>(errorCode, errorCode.getHttpStatus());
  }
}
