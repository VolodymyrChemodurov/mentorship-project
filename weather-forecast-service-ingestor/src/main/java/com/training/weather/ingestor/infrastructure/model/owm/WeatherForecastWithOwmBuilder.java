package com.training.weather.ingestor.infrastructure.model.owm;

import static java.util.Optional.ofNullable;

import com.training.weather.ingestor.core.entity.WeatherForecastRedisValue;
import com.training.weather.ingestor.core.model.owm.Clouds;
import com.training.weather.ingestor.core.model.owm.Forecast;
import com.training.weather.ingestor.core.model.owm.MainParameters;
import com.training.weather.ingestor.core.model.owm.Rain;
import com.training.weather.ingestor.core.model.owm.Snow;
import com.training.weather.ingestor.core.model.owm.Weather;
import com.training.weather.ingestor.core.model.owm.Wind;

import java.util.List;

public class WeatherForecastWithOwmBuilder {
  private WeatherForecastRedisValue weatherForecast;

  /**
   * Method for building WeatherForecastRedisValue from OwmResponse.
   * @param forecast Weather Forecast.
   * @return WeatherForecastWithOwmBuilder.
   */
  public WeatherForecastWithOwmBuilder withForecast(Forecast forecast) {
    weatherForecast = new WeatherForecastRedisValue();

    return this.withMainParameters(forecast.getMainParameters())
            .withWeathers(forecast.getWeather())
            .withClouds(forecast.getClouds())
            .withWind(forecast.getWind())
            .withRain(forecast.getRain())
            .withSnow(forecast.getSnow())
            .withTimestamp(forecast.getTimestamp())
            .withDate(forecast.getDate());
  }

  private WeatherForecastWithOwmBuilder withMainParameters(MainParameters mainParameters) {
    this.weatherForecast.setTemperature(mainParameters.getTemperature());
    this.weatherForecast.setMinTemperature(mainParameters.getMinTemperature());
    this.weatherForecast.setMaxTemperature(mainParameters.getMaxTemperature());
    this.weatherForecast.setPressure(mainParameters.getPressure());
    this.weatherForecast.setSeaLevelPressure(mainParameters.getSeaLevelPressure());
    this.weatherForecast.setGroundLevelPressure(mainParameters.getGroundLevelPressure());
    this.weatherForecast.setHumidity(mainParameters.getHumidity());
    return this;
  }

  private WeatherForecastWithOwmBuilder withWeathers(List<Weather> weathers) {
    this.weatherForecast.setWeather(weathers);
    return this;
  }

  private WeatherForecastWithOwmBuilder withClouds(Clouds clouds) {
    this.weatherForecast.setCloudsVolume(ofNullable(clouds).map(Clouds::getVolume).orElse(0.0));
    return this;
  }

  private WeatherForecastWithOwmBuilder withWind(Wind wind) {
    this.weatherForecast.setWindDegree(wind.getDegree());
    this.weatherForecast.setWindSpeed(wind.getSpeed());
    return this;
  }

  private WeatherForecastWithOwmBuilder withRain(Rain rain) {
    this.weatherForecast.setRainVolume(ofNullable(rain).map(Rain::getVolume).orElse(0.0));
    return this;
  }

  private WeatherForecastWithOwmBuilder withSnow(Snow snow) {
    this.weatherForecast.setSnowVolume(ofNullable(snow).map(Snow::getVolume).orElse(0.0));
    return this;
  }

  private WeatherForecastWithOwmBuilder withTimestamp(int timestamp) {
    this.weatherForecast.setTimestamp(timestamp);
    return this;
  }

  private WeatherForecastWithOwmBuilder withDate(String date) {
    this.weatherForecast.setDate(date);
    return this;
  }

  public WeatherForecastRedisValue createWeatherForecast() {
    return this.weatherForecast;
  }
}