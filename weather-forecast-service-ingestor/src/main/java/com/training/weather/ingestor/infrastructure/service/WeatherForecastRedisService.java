package com.training.weather.ingestor.infrastructure.service;

import com.training.weather.ingestor.core.entity.WeatherForecastRedisKey;
import com.training.weather.ingestor.core.entity.WeatherForecastRedisValue;
import com.training.weather.ingestor.core.entity.WeatherForecastRedisWrapper;
import com.training.weather.ingestor.core.model.owm.City;
import com.training.weather.ingestor.core.model.owm.Coordinates;
import com.training.weather.ingestor.core.repository.GeoIndexedKeyValueRepository;
import com.training.weather.ingestor.core.service.WeatherForecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WeatherForecastRedisService
        implements WeatherForecastService<WeatherForecastRedisWrapper> {
  private static final Logger LOG = LoggerFactory.getLogger(WeatherForecastRedisService.class);

  private final GeoIndexedKeyValueRepository<WeatherForecastRedisKey,
          WeatherForecastRedisValue> weatherForecastRepository;

  public WeatherForecastRedisService(
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

    LOG.info("Stored into Redis.");
  }
}
