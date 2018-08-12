package com.training.weather.ingestor.core.model;

public class WeatherForecastRedisWrapper<K extends WeatherForecastRedisKey,
        V extends WeatherForecastRedisValue> implements WeatherForecastKeyValueWrapper<K, V> {

  private K weatherForecastKey;

  private V weatherForecastValue;

  @Override
  public void setKey(K weatherForecastKey) {
    this.weatherForecastKey = weatherForecastKey;
  }

  @Override
  public K getKey() {
    return weatherForecastKey;
  }

  @Override
  public void setValue(V weatherForecastValue) {
    this.weatherForecastValue = weatherForecastValue;
  }

  @Override
  public V getValue() {
    return weatherForecastValue;
  }
}
