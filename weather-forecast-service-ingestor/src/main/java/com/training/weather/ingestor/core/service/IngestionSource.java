package com.training.weather.ingestor.core.service;

import com.training.weather.ingestor.core.model.City;

import java.util.List;

public interface IngestionSource {
  List<City> get();

  void reset();
}
