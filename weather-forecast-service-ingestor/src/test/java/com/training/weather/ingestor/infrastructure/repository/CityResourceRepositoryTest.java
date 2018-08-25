package com.training.weather.ingestor.infrastructure.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.weather.core.model.City;
import com.training.weather.ingestor.infrastructure.resources.ResourceLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CityResourceRepositoryTest {

  private ResourceLoader resourceLoader;

  @Before
  public void setup() {
    resourceLoader = new ResourceLoader(new ObjectMapper());
  }

  @Test
  public void testGetAllExpectedNumberOfCities() {
    CityResourceRepository target =
        new CityResourceRepository("/two_test_cities.json", resourceLoader);

    List<City> result = target.getAll();

    Assert.assertEquals(2, result.size());
  }

  @Test
  public void testGetAllCityFieldsMapped() {
    CityResourceRepository target =
        new CityResourceRepository("/one_test_city.json", resourceLoader);

    List<City> result = target.getAll();

    City city = result.get(0);
    Assert.assertEquals("UA", city.getCountry());
    Assert.assertEquals("Lviv", city.getName());
    Assert.assertEquals(49.838261, city.getCoordinates().getLatitude(), 0.000001);
    Assert.assertEquals(24.023239, city.getCoordinates().getLongitude(), 0.000001);
  }
}