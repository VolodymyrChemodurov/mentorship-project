package com.training.weather.ingestor.infrastructure.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.weather.ingestor.core.model.City;
import com.training.weather.ingestor.core.model.Clouds;
import com.training.weather.ingestor.core.model.Coordinates;
import com.training.weather.ingestor.core.model.Forecast;
import com.training.weather.ingestor.core.model.MainParameters;
import com.training.weather.ingestor.core.model.Rain;
import com.training.weather.ingestor.core.model.Snow;
import com.training.weather.ingestor.core.model.Wind;
import com.training.weather.ingestor.infrastructure.entity.redis.OpenWeatherMapResponse;
import com.training.weather.ingestor.core.entity.WeatherForecast;
import com.training.weather.ingestor.core.entity.WeatherForecastKey;
import com.training.weather.ingestor.infrastructure.util.CityMixIn;
import com.training.weather.ingestor.infrastructure.util.CloudsMixIn;
import com.training.weather.ingestor.infrastructure.util.CoordinatesMixIn;
import com.training.weather.ingestor.infrastructure.util.ForecastMixIn;
import com.training.weather.ingestor.infrastructure.util.MainParametersMixIn;
import com.training.weather.ingestor.infrastructure.util.ObjectCodec;
import com.training.weather.ingestor.infrastructure.util.OpenWeatherMapResponseMixIn;
import com.training.weather.ingestor.infrastructure.util.RainMixIn;
import com.training.weather.ingestor.infrastructure.util.SnowMixIn;
import com.training.weather.ingestor.infrastructure.util.WindMixIn;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.codec.RedisCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Spring configuration and Bean definition.
 *
 * @author Oleg_Hudyma
 */
@Configuration
@ComponentScan(basePackages = "com.training.weather.ingestor.infrastructure")
@EnableScheduling
@EnableRedisRepositories
public class ApplicationConfig {

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

  /**
   * Defines message  converter based on Object Mapper.
   *
   * @param objectMapper ObjectMapper
   * @return {@link MappingJackson2HttpMessageConverter}
   */
  @Bean
  public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(
          ObjectMapper objectMapper) {
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

    converter.setObjectMapper(objectMapper);

    return converter;
  }

  /**
   * Defines Rest Template.
   *
   * @param JacksonMessageConverter MappingJackson2HttpMessageConverter
   * @return {@link RestTemplate}
   */
  @Bean
  public RestTemplate restTemplate(MappingJackson2HttpMessageConverter JacksonMessageConverter) {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getMessageConverters().removeIf(m -> m.getClass().getName()
            .equals(MappingJackson2HttpMessageConverter.class.getName()));
    restTemplate.getMessageConverters().add(JacksonMessageConverter);

    return restTemplate;
  }

  /**
   * Defines list of available cities.
   *
   * @param filename     String
   * @param objectMapper ObjectMapper
   * @return List&ltCity&gt
   * @throws IOException
   */
  @Bean
  public List<City> cities(@Value("${spring.city.json.filename}") String filename,
                           ObjectMapper objectMapper) throws IOException {
    InputStream inputStream = getClass().getResourceAsStream(filename);

    City[] cities = objectMapper.readValue(inputStream, City[].class);

    return Arrays.asList(cities);
  }

  @Bean
  public RedisCodec<WeatherForecastKey, WeatherForecast> redisCodec() {
    return new ObjectCodec<>();
  }

  @Bean
  public RedisClient redisClient(@Value("${redis.connection.string}") String connectioString) {
    return RedisClient.create(connectioString);
  }

  @Bean
  public StatefulRedisConnection<WeatherForecastKey, WeatherForecast> statefulRedisConnection(
          RedisCodec<WeatherForecastKey, WeatherForecast> redisCodec,
          RedisClient redisClient) {
    return redisClient.connect(redisCodec);
  }
}
