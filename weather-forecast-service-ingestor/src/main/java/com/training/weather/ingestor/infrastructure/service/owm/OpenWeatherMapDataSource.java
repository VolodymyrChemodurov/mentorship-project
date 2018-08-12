package com.training.weather.ingestor.infrastructure.service.owm;

import com.training.weather.ingestor.core.model.WeatherForecastWrapper;
import com.training.weather.ingestor.core.model.owm.City;
import com.training.weather.ingestor.core.model.owm.Coordinates;
import com.training.weather.ingestor.core.service.WeatherDataSource;
import com.training.weather.ingestor.core.util.Mapper;
import com.training.weather.ingestor.infrastructure.model.owm.OpenWeatherMapResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class OpenWeatherMapDataSource implements WeatherDataSource {
  private static final Logger LOG = LoggerFactory.getLogger(OpenWeatherMapDataSource.class);

  private final String apiScheme;

  private final String apiHost;

  private final String apiKey;

  private final Mapper<OpenWeatherMapResponse> mapper;

  private final RestTemplate restTemplate;

  /**
   * @param apiScheme    Open Weather API scheme.
   * @param apiHost      Open Weather API host name.
   * @param apiKey       Open Weather API key.
   * @param mapper       Open Weather to internal Model Mapper.
   * @param restTemplate Rest Template.
   */
  public OpenWeatherMapDataSource(
          @Value("${owm.api.scheme}") String apiScheme,
          @Value("${owm.api.host}") String apiHost,
          @Value("${owm.api.key}") String apiKey,
          @Qualifier("OpenWeatherMapRedisMapper") Mapper<OpenWeatherMapResponse> mapper,
          RestTemplate restTemplate) {
    this.apiScheme = apiScheme;
    this.apiHost = apiHost;
    this.apiKey = apiKey;
    this.mapper = mapper;
    this.restTemplate = restTemplate;
  }

  /**
   * Method for retrieving forecasts from OpenWeather.
   */
  public List<WeatherForecastWrapper> getForecasts(City city) {
    Coordinates coordinates = city.getCoordinates();

    if (LOG.isInfoEnabled()) {
      LOG.info("Retrieving forecasts from OWM for: {}", coordinates);
    }

    ResponseEntity<OpenWeatherMapResponse> response = restTemplate
            .getForEntity(uri(coordinates), OpenWeatherMapResponse.class);

    OpenWeatherMapResponse openWeatherMapResponse = response.getBody();

    if (LOG.isInfoEnabled()) {
      LOG.info("Successfully retrieved forecasts");
    }

    List<WeatherForecastWrapper> wrapperList = mapper.map(openWeatherMapResponse);

    if (LOG.isInfoEnabled()) {
      LOG.info("Successfully retrieved forecasts " + openWeatherMapResponse);
    }

    return wrapperList;
  }

  private URI uri(Coordinates coordinates) {
    return UriComponentsBuilder.newInstance()
            .scheme(apiScheme)
            .host(apiHost)
            .path("/data/2.5/forecast")
            .query("lat={latitude}&lon={longitude}&appid={apiKey}")
            .buildAndExpand(
                    coordinates.getLatitude(),
                    coordinates.getLongitude(),
                    apiKey)
            .toUri();
  }
}
