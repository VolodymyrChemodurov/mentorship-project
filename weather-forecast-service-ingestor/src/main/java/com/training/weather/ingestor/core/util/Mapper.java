package com.training.weather.ingestor.core.util;

import com.training.weather.ingestor.core.model.WeatherForecastWrapper;

import java.util.List;

public interface Mapper<T> {
  List<WeatherForecastWrapper> map(T object);
}
