package com.training.weather.ingestor.core.model;

public interface WeatherForecastKeyValueWrapper<K extends WeatherForecastKey,
        V extends WeatherForecastValue> extends WeatherForecastWrapper {

  void setKey(K key);

  K getKey();

  void setValue(V value);

  V getValue();
}
