package com.training.weather.ingestor.infrastructure.service;

import com.training.weather.ingestor.infrastructure.entity.Coordinates;
import com.training.weather.ingestor.infrastructure.entity.OpenWeatherForecastResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OpenWeatherService {
  private static final String API_SCHEME = "http";
  private static final String API_HOST = "api.openweathermap.org";
  private static final String API_KEY = "a5b6e7041392ba7156b0d1de0e3e7923";

  @Autowired
  private RestTemplate restTemplate;

  /**
   * Method for retrieving forecasts from OpenWeather.
   */
  public OpenWeatherForecastResponse getForecastByCoordinates(Coordinates coordinates) {
    UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .scheme(API_SCHEME).host(API_HOST).path("/data/2.5/forecast")
            .query("lat={latitude}&lon={longitude}&appid={apiKey}")
            .buildAndExpand(coordinates.getLatitude(), coordinates.getLongitude(), API_KEY);

    ResponseEntity<OpenWeatherForecastResponse> response = restTemplate
            .getForEntity(uriComponents.toUri(), OpenWeatherForecastResponse.class);

    return response.getBody();
  }
}
