package com.training.weather.ingestor.infrastructure.repository;

import com.training.weather.ingestor.infrastructure.entity.redis.WeatherForecast;
import org.springframework.data.repository.CrudRepository;

public interface WeatherForecastRedisRepository extends
        CrudRepository<WeatherForecast, String> {
}
