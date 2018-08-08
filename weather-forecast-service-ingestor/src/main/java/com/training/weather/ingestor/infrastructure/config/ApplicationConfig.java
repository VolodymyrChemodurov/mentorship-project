package com.training.weather.ingestor.infrastructure.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.weather.ingestor.core.CityRepository;
import com.training.weather.ingestor.core.WeatherDataSource;
import com.training.weather.ingestor.core.WeatherForecastCachingFacade;
import com.training.weather.ingestor.core.WeatherForecastProcessor;
import com.training.weather.ingestor.core.WeatherForecastRepository;
import com.training.weather.ingestor.core.model.City;
import com.training.weather.ingestor.core.model.Clouds;
import com.training.weather.ingestor.core.model.Coordinates;
import com.training.weather.ingestor.core.model.MainParameters;
import com.training.weather.ingestor.core.model.Rain;
import com.training.weather.ingestor.core.model.Snow;
import com.training.weather.ingestor.core.model.WeatherForecast;
import com.training.weather.ingestor.core.model.WeatherForecastKey;
import com.training.weather.ingestor.core.model.Wind;
import com.training.weather.ingestor.infrastructure.jackson.CityMixIn;
import com.training.weather.ingestor.infrastructure.jackson.CloudsMixIn;
import com.training.weather.ingestor.infrastructure.jackson.CoordinatesMixIn;
import com.training.weather.ingestor.infrastructure.jackson.ForecastMixIn;
import com.training.weather.ingestor.infrastructure.jackson.MainParametersMixIn;
import com.training.weather.ingestor.infrastructure.jackson.OpenWeatherMapResponseMixIn;
import com.training.weather.ingestor.infrastructure.jackson.RainMixIn;
import com.training.weather.ingestor.infrastructure.jackson.SnowMixIn;
import com.training.weather.ingestor.infrastructure.jackson.WindMixIn;
import com.training.weather.ingestor.infrastructure.lettuce.ObjectCodec;
import com.training.weather.ingestor.infrastructure.owm.OpenWeatherMapForecast;
import com.training.weather.ingestor.infrastructure.owm.OpenWeatherMapResponse;
import com.training.weather.ingestor.infrastructure.repository.CityResourceRepository;
import com.training.weather.ingestor.infrastructure.resources.ResourceLoader;
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

  @Bean
  public WeatherForecastCachingFacade weatherForecastCachingFacade(
          WeatherDataSource weatherDataSource,
          WeatherForecastProcessor processor,
          CityRepository cityRepository) {
    return new WeatherForecastCachingFacade(
            weatherDataSource, processor, cityRepository);
  }

  @Bean
  public WeatherForecastProcessor processor(
          WeatherForecastRepository repository) {
    return new WeatherForecastProcessor(repository);
  }

  /**
   * ObjectMapper for deserializing json.
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
    objectMapper.addMixIn(OpenWeatherMapForecast.class, ForecastMixIn.class);
    objectMapper.addMixIn(OpenWeatherMapResponse.class, OpenWeatherMapResponseMixIn.class);

    return objectMapper;
  }

  /**
   * Defines message  converter based on Object Mapper.
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
   */
  @Bean
  public RestTemplate restTemplate(MappingJackson2HttpMessageConverter jacksonMessageConverter) {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getMessageConverters().removeIf(m -> m.getClass().getName()
            .equals(MappingJackson2HttpMessageConverter.class.getName()));
    restTemplate.getMessageConverters().add(jacksonMessageConverter);

    return restTemplate;
  }

  @Bean
  public ResourceLoader resourceLoader(ObjectMapper mapper) {
    return new ResourceLoader(mapper);
  }

  @Bean
  public CityRepository cityRepository(ResourceLoader resourceLoader) {
    return new CityResourceRepository("/cities.json", resourceLoader);
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
