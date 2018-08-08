package com.training.weather.ingestor.infrastructure.repository;

import com.fasterxml.jackson.databind.type.TypeFactory;
import com.training.weather.ingestor.core.CityRepository;
import com.training.weather.ingestor.core.model.City;
import com.training.weather.ingestor.infrastructure.resources.ResourceLoader;

import java.util.List;

public class CityResourceRepository implements CityRepository {

  private final List<City> cities;

  /**
   * Initialization of CityResourceRepository.
   */
  public CityResourceRepository(
          String filename, ResourceLoader resourceLoader) {
    this.cities = resourceLoader.getResourceAsValue(
            TypeFactory.defaultInstance().constructCollectionType(List.class, City.class),
            filename);
  }

  @Override
  public List<City> getAll() {
    return cities;
  }
}
