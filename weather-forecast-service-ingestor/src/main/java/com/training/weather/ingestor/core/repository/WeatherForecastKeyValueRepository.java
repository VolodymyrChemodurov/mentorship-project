package com.training.weather.ingestor.core.repository;

import com.training.weather.ingestor.core.model.WeatherForecastKey;
import com.training.weather.ingestor.core.model.WeatherForecastValue;

public interface WeatherForecastKeyValueRepository<K extends WeatherForecastKey,
        V extends WeatherForecastValue> extends WeatherForecastRepository {
}
