package com.training.weather.ingestor.infrastructure.service;

import com.training.weather.ingestor.infrastructure.entity.City;
import com.training.weather.ingestor.infrastructure.entity.Forecast;
import com.training.weather.ingestor.infrastructure.entity.redis.WeatherForecast;
import com.training.weather.ingestor.infrastructure.repository.WeatherForecastRedisRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service
public class WeatherForecastRedisService {
  private static final Logger LOG = Logger.getLogger(WeatherForecastRedisService.class);

  @Autowired
  private WeatherForecastRedisRepository weatherForecastRedisRepository;

  /**
   * Method for storing weather forecast to Redis.
   */

  public void save(Forecast forecast, City city) {
    double lat = city.getCoordinates().getLatitude();
    double lon = city.getCoordinates().getLongitude();

    Point point = new Point(lat, lon);
    WeatherForecast weatherForecast = new WeatherForecast(null, forecast, city.getName(), point);

    weatherForecastRedisRepository.save(weatherForecast);

    LOG.info("Stored to Redis.");
  }
}
