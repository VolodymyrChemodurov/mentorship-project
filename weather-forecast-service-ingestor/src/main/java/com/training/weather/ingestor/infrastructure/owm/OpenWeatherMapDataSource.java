package com.training.weather.ingestor.infrastructure.owm;

import com.training.weather.ingestor.core.WeatherDataSource;
import com.training.weather.ingestor.core.model.Coordinates;
import com.training.weather.ingestor.core.model.WeatherForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpenWeatherMapDataSource implements WeatherDataSource {
  private static final Logger LOG = LoggerFactory.getLogger(OpenWeatherMapDataSource.class);

  @Value("${owm.api.scheme}")
  private String scheme;

  @Value("${owm.api.host}")
  private String host;

  @Value("${owm.api.key}")
  private String apiKey;

  private final RestTemplate restTemplate;

  public OpenWeatherMapDataSource(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  /**
   * Method for retrieving forecasts from OpenWeather.
   */
  public List<WeatherForecast> getForecasts(Coordinates coordinates) {
    LOG.info("Retrieving forecasts from OWM for: {}", coordinates);

    OpenWeatherMapResponse response = restTemplate
            .getForEntity(uri(coordinates), OpenWeatherMapResponse.class)
            .getBody();

    LOG.info("Successfully retrieved forecasts");
    return response.getForecasts().stream()
            .map(forecast ->
                    new WeatherForecastBuilder()
                            .withForecast(forecast)
                            .withCoordinates(coordinates)
                            .build())
            .collect(Collectors.toList());
  }

  private URI uri(Coordinates coordinates) {
    return UriComponentsBuilder.newInstance()
            .scheme(scheme)
            .host(host)
            .path("/data/2.5/forecast")
            .query("lat={latitude}&lon={longitude}&appid={apiKey}")
            .buildAndExpand(
                    coordinates.getLatitude(),
                    coordinates.getLongitude(),
                    apiKey)
            .toUri();
  }
}
