package com.training.weather.ingestor.infrastructure.repository;

import com.training.weather.ingestor.core.model.owm.City;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityRepository {

  private final List<City> cities;

  /**
   * Constructor for City.
   *
   * @param cityList List&ltCity&gt
   */
  public CityRepository(List<City> cityList) {
    this.cities = cityList;
  }

  public List<City> get() {
    return cities;
  }
}
