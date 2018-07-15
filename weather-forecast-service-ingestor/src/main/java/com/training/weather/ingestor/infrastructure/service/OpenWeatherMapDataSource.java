package com.training.weather.ingestor.infrastructure.service;

import com.training.weather.ingestor.core.model.Coordinates;
import com.training.weather.ingestor.core.model.OpenWeatherMapResponse;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class OpenWeatherMapDataSource implements WeatherDataSource {
  private static final Logger LOG = Logger.getLogger(OpenWeatherMapDataSource.class);

  private static final String API_SCHEME = "http";
  private static final String API_HOST = "api.openweathermap.org";
  private static final String API_KEY = "a5b6e7041392ba7156b0d1de0e3e7923";

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

    ResponseEntity<OpenWeatherMapResponse> response = restTemplate
            .getForEntity(uriComponents.toUri(), OpenWeatherMapResponse.class);

    return response.getBody();
  }
}
