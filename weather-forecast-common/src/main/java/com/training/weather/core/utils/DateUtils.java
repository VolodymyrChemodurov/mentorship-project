package com.training.weather.core.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtils {
  private static final DateTimeFormatter WEATHER_FORECAST_DATE_FORMAT;

  static {
    WEATHER_FORECAST_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
  }

  private DateUtils() {
  }

  public static String key(LocalDateTime date) {
    return WEATHER_FORECAST_DATE_FORMAT.format(date);
  }
}
