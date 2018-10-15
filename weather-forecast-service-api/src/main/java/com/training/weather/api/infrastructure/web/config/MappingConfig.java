package com.training.weather.api.infrastructure.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class MappingConfig {
  /**
   * ObjectMapper for deserializing json.
   *
   * @return {@link ObjectMapper}
   */
  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();

    JavaTimeModule javaTimeModule = new JavaTimeModule();

    objectMapper.registerModule(javaTimeModule);

    return objectMapper;
  }

  @Bean
  public Supplier<LocalDateTime> currentDateSupplier() {
    return LocalDateTime::now;
  }

  /**
   * Method configuring Function for parsing OWM time string.
   *
   * @return Function.
   */
  @Bean
  public Function<String, LocalDateTime> dateTimeSupplier() {
    return (date) -> {
      DateTimeFormatter weatherMapDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

      return LocalDateTime.parse(date, weatherMapDateFormat);
    };
  }
}
