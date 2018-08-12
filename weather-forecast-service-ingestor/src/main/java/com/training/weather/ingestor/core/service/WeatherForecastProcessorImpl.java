package com.training.weather.ingestor.core.service;

import com.training.weather.ingestor.core.model.WeatherForecastRedisKey;
import com.training.weather.ingestor.core.model.WeatherForecastRedisValue;
import com.training.weather.ingestor.core.model.WeatherForecastRedisWrapper;
import com.training.weather.ingestor.core.model.owm.City;
import com.training.weather.ingestor.core.model.owm.Coordinates;
import com.training.weather.ingestor.core.repository.GeoIndexedKeyValueRepository;

public class WeatherForecastProcessorImpl
        implements WeatherForecastProcessor<WeatherForecastRedisWrapper> {

  private final GeoIndexedKeyValueRepository<WeatherForecastRedisKey,
          WeatherForecastRedisValue> weatherForecastRepository;

  public WeatherForecastProcessorImpl(
          GeoIndexedKeyValueRepository weatherForecastRepository) {
    this.weatherForecastRepository = weatherForecastRepository;
  }

  /**
   * Method for storing weather forecast to Redis.
   */
  public void save(WeatherForecastRedisWrapper wrapper, City city) {
    Coordinates coordinates = city.getCoordinates();

    WeatherForecastRedisKey key = wrapper.getKey();

    WeatherForecastRedisValue value = wrapper.getValue();

    weatherForecastRepository.save(key, value, coordinates);
  }
}
