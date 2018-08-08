package com.training.weather.ingestor.infrastructure.owm;

import com.training.weather.ingestor.core.model.Clouds;
import com.training.weather.ingestor.core.model.Coordinates;
import com.training.weather.ingestor.core.model.MainParameters;
import com.training.weather.ingestor.core.model.Rain;
import com.training.weather.ingestor.core.model.Snow;
import com.training.weather.ingestor.core.model.Weather;
import com.training.weather.ingestor.core.model.WeatherForecast;
import com.training.weather.ingestor.core.model.Wind;

import java.util.List;
import java.util.Optional;

public class WeatherForecastBuilder {
  private WeatherForecast weatherForecast;

  /**
   * Maps OpenWeatherMapForecast to WeatherForecast.
   */
  public WeatherForecastBuilder withForecast(OpenWeatherMapForecast forecast) {
    weatherForecast = new WeatherForecast();

    return this.withMainParameters(
            forecast.getMainParameters())
            .withWeathers(forecast.getWeather())
            .withClouds(forecast.getClouds())
            .withWind(forecast.getWind())
            .withRain(forecast.getRain())
            .withSnow(forecast.getSnow())
            .withTimestamp(forecast.getTimestamp())
            .withDate(forecast.getDate());
  }

  private WeatherForecastBuilder withMainParameters(MainParameters mainParameters) {
    this.weatherForecast.setTemperature(mainParameters.getTemperature());
    this.weatherForecast.setMinTemperature(mainParameters.getMinTemperature());
    this.weatherForecast.setMaxTemperature(mainParameters.getMaxTemperature());
    this.weatherForecast.setPressure(mainParameters.getPressure());
    this.weatherForecast.setSeaLevelPressure(mainParameters.getSeaLevelPressure());
    this.weatherForecast.setGroundLevelPressure(mainParameters.getGroundLevelPressure());
    this.weatherForecast.setHumidity(mainParameters.getHumidity());
    return this;
  }

  /**
   * Sets coordinates of forecast.
   */
  public WeatherForecastBuilder withCoordinates(Coordinates coordinates) {
    this.weatherForecast.setLatitude(coordinates.getLatitude());
    this.weatherForecast.setLongitude(coordinates.getLongitude());
    return this;
  }

  private WeatherForecastBuilder withWeathers(List<Weather> weathers) {
    this.weatherForecast.setWeather(weathers);
    return this;
  }

  private WeatherForecastBuilder withClouds(Clouds clouds) {
    this.weatherForecast.setCloudsVolume(Optional.ofNullable(clouds)
            .map(Clouds::getVolume)
            .orElse(0));
    return this;
  }

  private WeatherForecastBuilder withWind(Wind wind) {
    this.weatherForecast.setWindDegree(wind.getDegree());
    this.weatherForecast.setWindSpeed(wind.getSpeed());
    return this;
  }

  private WeatherForecastBuilder withRain(Rain rain) {
    this.weatherForecast.setRainVolume(Optional.ofNullable(rain).map(Rain::getVolume).orElse(0.0));
    return this;
  }

  private WeatherForecastBuilder withSnow(Snow snow) {
    this.weatherForecast.setSnowVolume(Optional.ofNullable(snow).map(Snow::getVolume).orElse(0.0));
    return this;
  }

  private WeatherForecastBuilder withTimestamp(int timestamp) {
    this.weatherForecast.setTimestamp(timestamp);
    return this;
  }

  private WeatherForecastBuilder withDate(String date) {
    this.weatherForecast.setDate(date);
    return this;
  }

  public WeatherForecast build() {
    return this.weatherForecast;
  }
}