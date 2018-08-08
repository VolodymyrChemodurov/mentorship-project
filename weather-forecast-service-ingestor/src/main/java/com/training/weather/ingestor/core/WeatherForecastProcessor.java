package com.training.weather.ingestor.core;

import com.training.weather.ingestor.core.model.Coordinates;
import com.training.weather.ingestor.core.model.WeatherForecast;
import com.training.weather.ingestor.core.model.WeatherForecastKey;

public class WeatherForecastProcessor {

  private final WeatherForecastRepository repository;

  public WeatherForecastProcessor(WeatherForecastRepository repository) {
    this.repository = repository;
  }

  public void process(WeatherForecast forecast) {
    repository.save(key(forecast), forecast);
  }

  private WeatherForecastKey key(WeatherForecast forecast) {
    WeatherForecastKey key = new WeatherForecastKey();
    key.setCoordinates(new Coordinates(
            forecast.getLatitude(), forecast.getLongitude()));
    key.setTimestamp(forecast.getTimestamp());
    return key;
  }
}
