package com.training.weather.ingestor.core.repository;

import com.training.weather.ingestor.core.model.WeatherForecastKey;
import com.training.weather.ingestor.core.model.WeatherForecastValue;
import com.training.weather.ingestor.core.model.owm.Coordinates;

public interface GeoIndexedKeyValueRepository<K extends WeatherForecastKey,
        V extends WeatherForecastValue> extends WeatherForecastKeyValueRepository<K, V> {
  void save(K key, V value, Coordinates coordinates);
}
