package com.training.weather.ingestor.infrastructure.job;

import com.training.weather.ingestor.core.model.City;
import com.training.weather.ingestor.core.service.IngestionSource;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BatchedIngestionSource implements IngestionSource {

  private final int batchSize;
  private final List<City> cities;
  private final AtomicInteger currentBatch = new AtomicInteger(-1);

  public BatchedIngestionSource(int batchSize, List<City> cities) {
    this.cities = cities;
    this.batchSize = batchSize;
  }

  @Override
  public List<City> get() {
    int startIndex = currentBatch.incrementAndGet() * batchSize;
    if (startIndex >= cities.size()) {
      return Collections.emptyList();
    }

    int endIndex = startIndex + batchSize;
    if (endIndex > cities.size()) {
      endIndex = cities.size();
    }

    return cities.subList(startIndex, endIndex);
  }

  @Override
  public void reset() {
    currentBatch.set(-1);
  }
}
