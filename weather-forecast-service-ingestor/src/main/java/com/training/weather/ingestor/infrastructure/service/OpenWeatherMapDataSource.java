package com.training.weather.ingestor.infrastructure.service;

import com.training.weather.ingestor.core.model.Coordinates;
import com.training.weather.ingestor.core.service.WeatherDataSource;
import com.training.weather.ingestor.infrastructure.entity.redis.OpenWeatherMapResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OpenWeatherMapDataSource implements WeatherDataSource {
  private static final Logger LOG = LoggerFactory.getLogger(OpenWeatherMapDataSource.class);

  @Value("${owm.api.scheme}")
  private String API_SCHEME;

  @Value("${owm.api.host}")
  private String API_HOST;

  @Value("${owm.api.key}")
  private String API_KEY;

  private final RestTemplate restTemplate;

  public OpenWeatherMapDataSource(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  /**
   * Method for retrieving forecasts from OpenWeather.
   */
  public OpenWeatherMapResponse getForecasts(Coordinates coordinates) {
    UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .scheme(API_SCHEME).host(API_HOST).path("/data/2.5/forecast")
            .query("lat={latitude}&lon={longitude}&appid={apiKey}")
            .buildAndExpand(
                    coordinates.getLatitude(),
                    coordinates.getLongitude(), API_KEY);

    LOG.info("Retrieving forecasts from OWM for: " + coordinates);
    ResponseEntity<OpenWeatherMapResponse> response = restTemplate
            .getForEntity(uriComponents.toUri(), OpenWeatherMapResponse.class);
    OpenWeatherMapResponse openWeatherMapResponse = response.getBody();
    openWeatherMapResponse.setCoordinates(coordinates);

    LOG.info("Successfully retrieved forecasts " + openWeatherMapResponse);
    return openWeatherMapResponse;
  }
}
