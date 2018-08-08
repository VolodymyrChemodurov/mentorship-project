package com.training.weather.ingestor.infrastructure.resources;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public final class ResourceLoader {

  private final ObjectMapper mapper;

  public ResourceLoader(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  /**
   * Reads content of file and converts it to specified type.
   */
  public <T> T getResourceAsValue(JavaType type, String filename) {
    try (InputStream stream = ResourceLoader.class.getResourceAsStream(filename)) {
      return mapper.readValue(stream, type);
    } catch (IOException ex) {
      throw new IllegalArgumentException(ex);
    }
  }
}
