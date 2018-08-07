package com.training.weather.ingestor.infrastructure.util;

import com.training.weather.ingestor.core.entity.WeatherForecastWrapper;

import java.util.List;

public interface Mapper<T> {
  List<WeatherForecastWrapper> map(T object);
}
