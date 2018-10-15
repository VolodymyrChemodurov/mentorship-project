package com.training.weather.api.core.service;

import com.training.weather.api.infrastructure.web.exception.Error;
import com.training.weather.api.infrastructure.web.exception.ServiceException;
import com.training.weather.core.model.Coordinates;
import com.training.weather.core.model.WeatherForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Optional;

public class WeatherForecastFacadeImpl implements WeatherForecastFacade {
  private static final Logger LOG = LoggerFactory.getLogger(WeatherForecastFacadeImpl.class);

  private final WeatherForecastService weatherForecastService;

  /**
   * Constructor.
   * @param weatherForecastService WeatherForecastService implementation.
   */
  public WeatherForecastFacadeImpl(WeatherForecastService weatherForecastService) {
    this.weatherForecastService = weatherForecastService;
  }

  /**
   * Method for retrieving WeatherForecast by coordinates.
   *
   * @param longitude longitude.
   * @param latitude  latitude.
   * @return WeatherForecast.
   */
  public WeatherForecast getWeatherForecastByCoordinates(double longitude,
                                                         double latitude,
                                                         LocalDateTime dateTime) {
    Coordinates coordinates = new Coordinates();

    coordinates.setLongitude(longitude);
    coordinates.setLatitude(latitude);

    Optional<WeatherForecast> weatherForecast = weatherForecastService
            .getWeatherForecastByCoordinates(coordinates, dateTime);

    if (weatherForecast.isPresent()) {
      return weatherForecast.get();
    } else {
      LOG.warn("Not forecasts found with coordinates: " + coordinates + " and time: " + dateTime);
      throw new ServiceException(Error.FORECAST_NOT_FOUND);
    }
  }
}