package com.training.weather.ingestor.core.repository;

import com.training.weather.ingestor.core.model.owm.City;

import java.util.List;

public interface CityRepository {
  List<City> getAll();
}
