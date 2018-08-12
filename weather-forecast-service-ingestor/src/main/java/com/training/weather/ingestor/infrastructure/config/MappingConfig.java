package com.training.weather.ingestor.infrastructure.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.weather.ingestor.core.model.owm.City;
import com.training.weather.ingestor.core.model.owm.Clouds;
import com.training.weather.ingestor.core.model.owm.Coordinates;
import com.training.weather.ingestor.core.model.owm.Forecast;
import com.training.weather.ingestor.core.model.owm.MainParameters;
import com.training.weather.ingestor.core.model.owm.Rain;
import com.training.weather.ingestor.core.model.owm.Snow;
import com.training.weather.ingestor.core.model.owm.Wind;
import com.training.weather.ingestor.infrastructure.jackson.owm.CityMixIn;
import com.training.weather.ingestor.infrastructure.jackson.owm.CloudsMixIn;
import com.training.weather.ingestor.infrastructure.jackson.owm.CoordinatesMixIn;
import com.training.weather.ingestor.infrastructure.jackson.owm.ForecastMixIn;
import com.training.weather.ingestor.infrastructure.jackson.owm.MainParametersMixIn;
import com.training.weather.ingestor.infrastructure.jackson.owm.OpenWeatherMapResponseMixIn;
import com.training.weather.ingestor.infrastructure.jackson.owm.RainMixIn;
import com.training.weather.ingestor.infrastructure.jackson.owm.SnowMixIn;
import com.training.weather.ingestor.infrastructure.jackson.owm.WindMixIn;
import com.training.weather.ingestor.infrastructure.model.owm.OpenWeatherMapResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    objectMapper.addMixIn(Coordinates.class, CoordinatesMixIn.class);
    objectMapper.addMixIn(City.class, CityMixIn.class);
    objectMapper.addMixIn(Clouds.class, CloudsMixIn.class);
    objectMapper.addMixIn(Wind.class, WindMixIn.class);
    objectMapper.addMixIn(Rain.class, RainMixIn.class);
    objectMapper.addMixIn(Snow.class, SnowMixIn.class);
    objectMapper.addMixIn(MainParameters.class, MainParametersMixIn.class);
    objectMapper.addMixIn(Forecast.class, ForecastMixIn.class);
    objectMapper.addMixIn(OpenWeatherMapResponse.class, OpenWeatherMapResponseMixIn.class);

    return objectMapper;
  }
}
