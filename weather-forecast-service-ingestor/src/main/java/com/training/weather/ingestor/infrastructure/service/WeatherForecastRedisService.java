package com.training.weather.ingestor.infrastructure.service;

import com.training.weather.ingestor.core.model.City;
import com.training.weather.ingestor.core.model.Coordinates;
import com.training.weather.ingestor.core.model.Forecast;
import com.training.weather.ingestor.infrastructure.entity.redis.WeatherForecast;
import com.training.weather.ingestor.infrastructure.repository.WeatherForecastRedisRepository;
import org.apache.log4j.Logger;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service
public class WeatherForecastRedisService {
  private static final Logger LOG = Logger.getLogger(WeatherForecastRedisService.class);

  private final WeatherForecastRedisRepository weatherForecastRedisRepository;

  public WeatherForecastRedisService(
          WeatherForecastRedisRepository weatherForecastRedisRepository) {
    this.weatherForecastRedisRepository = weatherForecastRedisRepository;
  }

  /**
   * Method for storing weather forecast to Redis.
   */

  public void save(Forecast forecast, City city) {
    Coordinates coordinates = city.getCoordinates();

    double lat = coordinates.getLatitude();
    double lon = coordinates.getLongitude();

    Point point = new Point(lat, lon);
    WeatherForecast weatherForecast = new WeatherForecast(forecast, city.getName(), point);

    weatherForecastRedisRepository.save(weatherForecast);

    LOG.info("Stored to Redis.");
  }
}
