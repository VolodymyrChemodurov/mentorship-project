package com.training.weather.ingestor.infrastructure.service.owm;

import com.training.weather.core.model.Coordinates;
import com.training.weather.core.model.WeatherForecast;
import com.training.weather.ingestor.infrastructure.model.owm.Forecast;
import com.training.weather.ingestor.infrastructure.model.owm.MainParameters;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class WeatherForecastTranslator {

  private final Supplier<LocalDateTime> currentDateSupplier;

  private final Function<String, LocalDateTime> owmDateParser;

  public WeatherForecastTranslator(Supplier<LocalDateTime> currentDateSupplier,
                                   Function<String, LocalDateTime> owmDateParser) {
    this.currentDateSupplier = currentDateSupplier;
    this.owmDateParser = owmDateParser;
  }

  /**
   * Translates Forecast and Coordinates into WeatherForecast.
   */
  public WeatherForecast from(Forecast forecast, Coordinates coordinates) {
    WeatherForecast weatherForecast = new WeatherForecast();
    weatherForecast.setCoordinates(coordinates);
    weatherForecast.setDate(owmDateParser.apply(forecast.getDate()));
    weatherForecast.setCreated(currentDateSupplier.get());

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
