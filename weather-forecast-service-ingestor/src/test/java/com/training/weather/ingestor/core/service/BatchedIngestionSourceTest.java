package com.training.weather.ingestor.core.service;

import com.training.weather.ingestor.core.model.City;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BatchedIngestionSourceTest {

  @Test
  public void testNextBatchSizeIsBiggerThanCitiesCount() {
    IngestionSource target =
        new BatchedIngestionSource(10, generateCities(6));

    List<City> result = target.get();

    Assert.assertEquals(6, result.size());
  }

  @Test
  public void testNextBatchSizeIsLessThanCitiesCount() {
    IngestionSource target =
        new BatchedIngestionSource(5, generateCities(10));

    List<City> result = target.get();

    Assert.assertEquals(5, result.size());
  }

  @Test
  public void testNextBatchSizeIsEqualToCitiesCount() {
    IngestionSource target =
        new BatchedIngestionSource(5, generateCities(5));

    List<City> result = target.get();

    Assert.assertEquals(5, result.size());
  }

  @Test
  public void testNextNoCitiesReturnedWhenAllCitiesWereRetrieved() {
    IngestionSource target =
        new BatchedIngestionSource(5, generateCities(10));

    target.get();
    target.get();
    List<City> result = target.get();

    Assert.assertEquals(0, result.size());
  }

  @Test
  public void testNextNumberOfCitiesIsNotDividableByBatchSize() {
    IngestionSource target =
        new BatchedIngestionSource(5, generateCities(12));

    target.get();
    target.get();
    List<City> result = target.get();

    Assert.assertEquals(2, result.size());
  }

  @Test
  public void testResetWhenAllCitiesWereRetrieved() {
    IngestionSource target =
        new BatchedIngestionSource(5, generateCities(10));

    List<City> firstResult = target.get();
    target.get();
    target.reset();
    List<City> secondResult = target.get();

    Assert.assertEquals(firstResult, secondResult);
  }

  @Test
  public void testResetWhenAllCitiesAreNotYetRetrieved() {
    IngestionSource target =
        new BatchedIngestionSource(5, generateCities(10));

    List<City> firstResult = target.get();
    target.reset();
    List<City> secondResult = target.get();

    Assert.assertEquals(firstResult, secondResult);
  }

  private List<City> generateCities(int size) {
    return IntStream.range(0, size)
        .mapToObj(i -> new City())
        .collect(Collectors.toList());
  }
}