package com.training.weather.ingestor.infrastructure.entity.redis;

import com.training.weather.ingestor.core.entity.WeatherForecast;
import com.training.weather.ingestor.core.model.Clouds;
import com.training.weather.ingestor.core.model.Coordinates;
import com.training.weather.ingestor.core.model.Forecast;
import com.training.weather.ingestor.core.model.MainParameters;
import com.training.weather.ingestor.core.model.Rain;
import com.training.weather.ingestor.core.model.Snow;
import com.training.weather.ingestor.core.model.Weather;
import com.training.weather.ingestor.core.model.Wind;

import java.util.List;

import static java.util.Optional.ofNullable;

public class WeatherForecastWithOWMBuilder {
  private WeatherForecast weatherForecast;

  public WeatherForecastWithOWMBuilder withForecast(Forecast forecast) {
    weatherForecast = new WeatherForecast();

    return this.withMainParameters(forecast.getMainParameters()).
            withWeathers(forecast.getWeather()).
            withClouds(forecast.getClouds()).
            withWind(forecast.getWind()).
            withRain(forecast.getRain()).
            withSnow(forecast.getSnow()).
            withTimestamp(forecast.getTimestamp()).
            withDate(forecast.getDate());
  }

  private WeatherForecastWithOWMBuilder withMainParameters(MainParameters mainParameters) {
    this.weatherForecast.setTemperature(mainParameters.getTemperature());
    this.weatherForecast.setMinTemperature(mainParameters.getMinTemperature());
    this.weatherForecast.setMaxTemperature(mainParameters.getMaxTemperature());
    this.weatherForecast.setPressure(mainParameters.getPressure());
    this.weatherForecast.setSeaLevelPressure(mainParameters.getSeaLevelPressure());
    this.weatherForecast.setGroundLevelPressure(mainParameters.getGroundLevelPressure());
    this.weatherForecast.setHumidity(mainParameters.getHumidity());
    return this;
  }

  public WeatherForecastWithOWMBuilder withCoordinates(Coordinates coordinates){
    this.weatherForecast.setLatitude(coordinates.getLatitude());
    this.weatherForecast.setLongitude(coordinates.getLongitude());
    return this;
  }

  private WeatherForecastWithOWMBuilder withWeathers(List<Weather> weathers) {
    this.weatherForecast.setWeather(weathers);
    return this;
  }

  private WeatherForecastWithOWMBuilder withClouds(Clouds clouds) {
    this.weatherForecast.setCloudsVolume(ofNullable(clouds).map(Clouds::getVolume).orElse(0));
    return this;
  }

  private WeatherForecastWithOWMBuilder withWind(Wind wind) {
    this.weatherForecast.setWindDegree(wind.getDegree());
    this.weatherForecast.setWindSpeed(wind.getSpeed());
    return this;
  }

  private WeatherForecastWithOWMBuilder withRain(Rain rain) {
    this.weatherForecast.setRainVolume(ofNullable(rain).map(Rain::getVolume).orElse(0.0));
    return this;
  }

  private WeatherForecastWithOWMBuilder withSnow(Snow snow) {
    this.weatherForecast.setSnowVolume(ofNullable(snow).map(Snow::getVolume).orElse(0.0));
    return this;
  }

  private WeatherForecastWithOWMBuilder withTimestamp(int timestamp) {
    this.weatherForecast.setTimestamp(timestamp);
    return this;
  }

  private WeatherForecastWithOWMBuilder withDate(String date) {
    this.weatherForecast.setDate(date);
    return this;
  }

  public WeatherForecast createWeatherForecast() {
    return this.weatherForecast;
  }
}