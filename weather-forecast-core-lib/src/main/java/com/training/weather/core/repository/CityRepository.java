package com.training.weather.core.repository;

import com.training.weather.core.model.City;

import java.util.List;

public interface CityRepository {
  List<City> getAll();
}
