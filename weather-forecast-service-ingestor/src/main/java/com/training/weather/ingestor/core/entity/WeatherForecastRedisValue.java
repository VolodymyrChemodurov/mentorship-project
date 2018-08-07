package com.training.weather.ingestor.core.entity;

import com.training.weather.ingestor.core.model.owm.Weather;

import java.util.List;

public class WeatherForecastRedisValue implements WeatherForecastValue {

  private int timestamp;
  private double temperature;
  private double minTemperature;
  private double maxTemperature;
  private double pressure;
  private double seaLevelPressure;
  private double groundLevelPressure;
  private int humidity;
  private List<Weather> weather;
  private double cloudsVolume;
  private double windSpeed;
  private double windDegree;
  private double rainVolume;
  private double snowVolume;
  private String date;

  public int getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(int timestamp) {
    this.timestamp = timestamp;
  }

  public double getTemperature() {
    return temperature;
  }

  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }

  public double getMinTemperature() {
    return minTemperature;
  }

  public void setMinTemperature(double minTemperature) {
    this.minTemperature = minTemperature;
  }

  public double getMaxTemperature() {
    return maxTemperature;
  }

  public void setMaxTemperature(double maxTemperature) {
    this.maxTemperature = maxTemperature;
  }

  public double getPressure() {
    return pressure;
  }

  public void setPressure(double pressure) {
    this.pressure = pressure;
  }

  public double getSeaLevelPressure() {
    return seaLevelPressure;
  }

  public void setSeaLevelPressure(double seaLevelPressure) {
    this.seaLevelPressure = seaLevelPressure;
  }

  public double getGroundLevelPressure() {
    return groundLevelPressure;
  }

  public void setGroundLevelPressure(double groundLevelPressure) {
    this.groundLevelPressure = groundLevelPressure;
  }

  public int getHumidity() {
    return humidity;
  }

  public void setHumidity(int humidity) {
    this.humidity = humidity;
  }

  public List<Weather> getWeather() {
    return weather;
  }

  public void setWeather(List<Weather> weather) {
    this.weather = weather;
  }

  public double getCloudsVolume() {
    return cloudsVolume;
  }

  public void setCloudsVolume(double cloudsVolume) {
    this.cloudsVolume = cloudsVolume;
  }

  public double getWindSpeed() {
    return windSpeed;
  }

  public void setWindSpeed(double windSpeed) {
    this.windSpeed = windSpeed;
  }

  public double getWindDegree() {
    return windDegree;
  }

  public void setWindDegree(double windDegree) {
    this.windDegree = windDegree;
  }

  public double getRainVolume() {
    return rainVolume;
  }

  public void setRainVolume(double rainVolume) {
    this.rainVolume = rainVolume;
  }

  public double getSnowVolume() {
    return snowVolume;
  }

  public void setSnowVolume(double snowVolume) {
    this.snowVolume = snowVolume;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
}
