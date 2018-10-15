package com.training.weather.core.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Keys {
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

  private Keys() {
  }

  public static String key(LocalDateTime date) {
    return FORMATTER.format(date);
  }
}
