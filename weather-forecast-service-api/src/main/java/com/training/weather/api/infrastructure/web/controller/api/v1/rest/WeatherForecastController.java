package com.training.weather.api.infrastructure.web.controller.api.v1.rest;

import com.training.weather.api.core.service.WeatherForecastFacade;
import com.training.weather.core.model.WeatherForecast;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1")
public class WeatherForecastController {
  private final WeatherForecastFacade weatherForecastFacade;

  public WeatherForecastController(WeatherForecastFacade weatherForecastFacade) {
    this.weatherForecastFacade = weatherForecastFacade;
  }

  /**
   * Method for retrieving {@link WeatherForecast}.
   * @param longitude longitude.
   * @param latitude latitude.
   * @param dateTime Forecast time. Pattern: yyyy-MM-dd'T'HH.
   * @return {@link WeatherForecast}.
   */
  @GetMapping("forecast/coordinates/lon/{longitude}/lat/{latitude}/date/{date}")
  public ResponseEntity<WeatherForecast> getWeatherForecastByCoordinates(
          @PathVariable("longitude") double longitude,
          @PathVariable("latitude") double latitude,
          @PathVariable("date")
          @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH") LocalDateTime dateTime) {
    return new ResponseEntity<>(
            weatherForecastFacade.getWeatherForecastByCoordinates(longitude,
                    latitude,
                    dateTime), HttpStatus.OK);
  }
}
