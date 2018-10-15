package com.training.weather.ingestor.infrastructure.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.training.weather.ingestor.infrastructure.jackson.owm.CloudsMixIn;
import com.training.weather.ingestor.infrastructure.jackson.owm.ForecastMixIn;
import com.training.weather.ingestor.infrastructure.jackson.owm.MainParametersMixIn;
import com.training.weather.ingestor.infrastructure.jackson.owm.OpenWeatherMapResponseMixIn;
import com.training.weather.ingestor.infrastructure.jackson.owm.RainMixIn;
import com.training.weather.ingestor.infrastructure.jackson.owm.SnowMixIn;
import com.training.weather.ingestor.infrastructure.jackson.owm.WindMixIn;
import com.training.weather.ingestor.infrastructure.model.owm.Clouds;
import com.training.weather.ingestor.infrastructure.model.owm.Forecast;
import com.training.weather.ingestor.infrastructure.model.owm.MainParameters;
import com.training.weather.ingestor.infrastructure.model.owm.OpenWeatherMapResponse;
import com.training.weather.ingestor.infrastructure.model.owm.Rain;
import com.training.weather.ingestor.infrastructure.model.owm.Snow;
import com.training.weather.ingestor.infrastructure.model.owm.Wind;
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

    objectMapper.registerModule(new JavaTimeModule());

    objectMapper.addMixIn(Clouds.class, CloudsMixIn.class);
    objectMapper.addMixIn(Wind.class, WindMixIn.class);
    objectMapper.addMixIn(Rain.class, RainMixIn.class);
    objectMapper.addMixIn(Snow.class, SnowMixIn.class);
    objectMapper.addMixIn(MainParameters.class, MainParametersMixIn.class);
    objectMapper.addMixIn(Forecast.class, ForecastMixIn.class);
    objectMapper.addMixIn(OpenWeatherMapResponse.class, OpenWeatherMapResponseMixIn.class);

    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

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
