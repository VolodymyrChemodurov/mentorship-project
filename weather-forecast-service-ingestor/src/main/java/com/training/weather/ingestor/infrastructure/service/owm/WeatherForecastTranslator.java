package com.training.weather.ingestor.infrastructure.service.owm;

import com.training.weather.core.model.Coordinates;
import com.training.weather.core.model.WeatherForecast;
import com.training.weather.ingestor.infrastructure.model.owm.Forecast;
import com.training.weather.ingestor.infrastructure.model.owm.MainParameters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class WeatherForecastTranslator {

  private static final DateTimeFormatter WEATHER_MAP_DATE_FORMAT;

  static {
    WEATHER_MAP_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  }

  private WeatherForecastTranslator() {
  }

  /**
   * Translates Forecast and Coordinates into WeatherForecast.
   */
  public static WeatherForecast from(Forecast forecast, Coordinates coordinates) {
    WeatherForecast weatherForecast = new WeatherForecast();
    weatherForecast.setCoordinates(coordinates);
    weatherForecast.setDate(LocalDateTime.parse(forecast.getDate(), WEATHER_MAP_DATE_FORMAT));
    weatherForecast.setCreated(LocalDateTime.now());

    MainParameters mainParameters = forecast.getMainParameters();
    weatherForecast.setGroundLevelPressure(mainParameters.getGroundLevelPressure());
    weatherForecast.setHumidity(mainParameters.getHumidity());
    weatherForecast.setMaxTemperature(mainParameters.getMaxTemperature());
    weatherForecast.setMinTemperature(mainParameters.getMinTemperature());
    weatherForecast.setPressure(mainParameters.getPressure());
    weatherForecast.setSeaLevelPressure(mainParameters.getSeaLevelPressure());
    weatherForecast.setTemperature(mainParameters.getTemperature());

    if (forecast.getRain() != null) {
      weatherForecast.setRainVolume(forecast.getRain().getVolume());
    }

    if (forecast.getSnow() != null) {
      weatherForecast.setSnowVolume(forecast.getSnow().getVolume());
    }

    if (forecast.getClouds() != null) {
      weatherForecast.setCloudsVolume(forecast.getClouds().getVolume());
    }

    if (forecast.getWind() != null) {
      weatherForecast.setWindDegree(forecast.getWind().getDegree());
      weatherForecast.setWindSpeed(forecast.getWind().getSpeed());
    }

    return weatherForecast;
  }
}
