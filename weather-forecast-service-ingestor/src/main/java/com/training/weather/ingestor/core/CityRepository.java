package com.training.weather.ingestor.core;

import com.training.weather.ingestor.core.model.City;

import java.util.List;

public interface CityRepository {
  List<City> getAll();
}
