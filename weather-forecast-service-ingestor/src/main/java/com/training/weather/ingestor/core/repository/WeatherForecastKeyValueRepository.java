package com.training.weather.ingestor.core.repository;

import com.training.weather.ingestor.core.entity.WeatherForecastKey;
import com.training.weather.ingestor.core.entity.WeatherForecastValue;

public interface WeatherForecastKeyValueRepository<K extends WeatherForecastKey,
        V extends WeatherForecastValue> extends WeatherForecastRepository {
}
