package com.training.weather.ingestor.infrastructure.service;

import com.training.weather.ingestor.core.model.City;
import com.training.weather.ingestor.core.model.Coordinates;
import com.training.weather.ingestor.core.model.Forecast;
import com.training.weather.ingestor.core.entity.WeatherForecast;
import com.training.weather.ingestor.core.entity.WeatherForecastKey;
import com.training.weather.ingestor.core.service.WeatherForecastService;
import com.training.weather.ingestor.infrastructure.entity.redis.WeatherForecastWithOWMBuilder;
import com.training.weather.ingestor.core.repository.WeatherForecastRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WeatherForecastRedisService implements WeatherForecastService {
  private static final Logger LOG = LoggerFactory.getLogger(WeatherForecastRedisService.class);

  private final WeatherForecastRepository weatherForecastRepository;

  public WeatherForecastRedisService(
          WeatherForecastRepository weatherForecastRepository) {
    this.weatherForecastRepository = weatherForecastRepository;
  }

  /**
   * Method for storing weather forecast to Redis.
   */

  public void save(Forecast forecast, City city) {
    Coordinates coordinates = city.getCoordinates();

    WeatherForecastKey weatherForecastKey = new WeatherForecastKey();
    weatherForecastKey.setCoordinates(coordinates);
    weatherForecastKey.setTimestamp(forecast.getTimestamp());

    WeatherForecastWithOWMBuilder builder = new WeatherForecastWithOWMBuilder();
    builder.withForecast(forecast).withCoordinates(coordinates);

    WeatherForecast weatherForecast = builder.createWeatherForecast();
    LOG.info("Mapped OWM response to WeatherForecastKey " + weatherForecastKey + "\n WeatherForecast " + weatherForecast);

    weatherForecastRepository.save(weatherForecastKey, weatherForecast);

    LOG.info("Stored into Redis.");
    }
}
