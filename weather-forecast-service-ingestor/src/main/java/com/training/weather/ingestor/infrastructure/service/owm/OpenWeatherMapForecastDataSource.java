package com.training.weather.ingestor.infrastructure.service.owm;

import com.training.weather.core.model.Coordinates;
import com.training.weather.core.model.WeatherForecast;
import com.training.weather.ingestor.core.model.City;
import com.training.weather.ingestor.core.repository.WeatherForecastDataSource;
import com.training.weather.ingestor.infrastructure.model.owm.OpenWeatherMapResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OpenWeatherMapForecastDataSource implements WeatherForecastDataSource {
  private static final Logger LOG = LoggerFactory.getLogger(OpenWeatherMapForecastDataSource.class);

  private final String apiScheme;

  private final String apiHost;

  private final String apiKey;

  private final RestTemplate restTemplate;

  /**
   * @param apiScheme    Open Weather API scheme.
   * @param apiHost      Open Weather API host name.
   * @param apiKey       Open Weather API key.
   * @param restTemplate Rest Template.
   */
  public OpenWeatherMapForecastDataSource(
          String apiScheme,
          String apiHost,
          String apiKey,
          RestTemplate restTemplate) {
    this.apiScheme = apiScheme;
    this.apiHost = apiHost;
    this.apiKey = apiKey;
    this.restTemplate = restTemplate;
  }

  /**
   * Method for retrieving forecasts from OpenWeather.
   */
  public List<WeatherForecast> getForecasts(City city) {
    Coordinates coordinates = city.getCoordinates();

    if (LOG.isInfoEnabled()) {
      LOG.info("Retrieving forecasts from OWM for: {}", coordinates);
    }

    OpenWeatherMapResponse response = restTemplate
            .getForEntity(uri(coordinates), OpenWeatherMapResponse.class)
            .getBody();

    if (LOG.isInfoEnabled()) {
      LOG.info("Successfully retrieved forecasts");
    }

    if (response == null || response.getForecasts() == null) {
      if (LOG.isInfoEnabled()) {
        LOG.info("Received empty response");
      }

      return Collections.emptyList();
    }

    return response.getForecasts().stream()
            .map(forecast -> WeatherForecastTranslator.from(
                    forecast, city.getCoordinates()))
            .collect(Collectors.toList());
  }

  private URI uri(Coordinates coordinates) {
    return UriComponentsBuilder.newInstance()
            .scheme(apiScheme)
            .host(apiHost)
            .path("/data/2.5/forecast")
            .query("lat={latitude}&lon={longitude}&appid={apiKey}&units=metric")
            .buildAndExpand(
                    coordinates.getLatitude(),
                    coordinates.getLongitude(),
                    apiKey)
            .toUri();
  }
}
