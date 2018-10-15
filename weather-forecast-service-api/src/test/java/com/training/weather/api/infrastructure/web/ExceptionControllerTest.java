package com.training.weather.api.infrastructure.web;

import com.training.weather.api.infrastructure.web.controller.api.v1.rest.ExceptionController;
import com.training.weather.api.infrastructure.web.exception.ApiError;
import com.training.weather.api.infrastructure.web.exception.Error;
import com.training.weather.api.infrastructure.web.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class ExceptionControllerTest {

  private static final Error ERROR = Error.FORECAST_NOT_FOUND;

  private ServiceException serviceException;

  private ExceptionController exceptionController = new ExceptionController();

  @Before
  public void setUp() {
    prepareTestData();
  }

  @Test
  public void shouldMapServiceExceptionIntoApiError() {
    ResponseEntity<ApiError> result = exceptionController.defaultExceptionHandler(serviceException);

    assertEquals(Error.FORECAST_NOT_FOUND.getError(), result.getBody());
  }

  private void prepareTestData() {
    prepareServiceException();
  }

  private void prepareServiceException() {
    serviceException = new ServiceException(ERROR);
  }
}
