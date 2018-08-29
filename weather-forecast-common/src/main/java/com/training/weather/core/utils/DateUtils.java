package com.training.weather.core.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public final class DateUtils {
  private static final DateTimeFormatter WEATHER_MAP_DATE_FORMAT;
  private static final DateTimeFormatter WEATHER_FORECAST_DATE_FORMAT;

  static {
    WEATHER_MAP_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    WEATHER_FORECAST_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
  }

  private DateUtils() {
  }

  public static LocalDateTime toWeatherForecastDate(String date) {
    return LocalDateTime.parse(date, WEATHER_MAP_DATE_FORMAT);
  }

  public static String toWeatherMapDateString(LocalDateTime date) {
    return WEATHER_MAP_DATE_FORMAT.format(date);
  }

  public static String toWeatherForecastDateString(LocalDateTime date) {
    return WEATHER_FORECAST_DATE_FORMAT.format(date);
  }

  public static long timestamp(LocalDateTime dateTime) {
    return dateTime.toEpochSecond(ZoneOffset.UTC);
  }
}
