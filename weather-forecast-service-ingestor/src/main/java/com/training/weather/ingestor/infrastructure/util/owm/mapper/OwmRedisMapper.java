package com.training.weather.ingestor.infrastructure.util.owm.mapper;

import com.training.weather.ingestor.core.entity.WeatherForecastKeyValueWrapper;
import com.training.weather.ingestor.core.entity.WeatherForecastRedisKey;
import com.training.weather.ingestor.core.entity.WeatherForecastRedisValue;
import com.training.weather.ingestor.core.entity.WeatherForecastRedisWrapper;
import com.training.weather.ingestor.core.entity.WeatherForecastWrapper;
import com.training.weather.ingestor.infrastructure.model.owm.OwmResponse;
import com.training.weather.ingestor.infrastructure.model.owm.WeatherForecastWithOwmBuilder;
import com.training.weather.ingestor.infrastructure.util.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("OwmRedisMapper")
public class OwmRedisMapper<T extends OwmResponse> implements Mapper<T> {

  @Override
  public List<WeatherForecastWrapper> map(T object) {
    List<WeatherForecastWrapper> wrapperList = new ArrayList<>();

    object.getForecasts().forEach(forecast -> {
      WeatherForecastKeyValueWrapper<WeatherForecastRedisKey, WeatherForecastRedisValue> wrapper
              = new WeatherForecastRedisWrapper<>();

      WeatherForecastRedisKey weatherForecastKey = new WeatherForecastRedisKey();
      weatherForecastKey.setTimestamp(forecast.getTimestamp());
      weatherForecastKey.setCoordinates(object.getCoordinates());

      WeatherForecastWithOwmBuilder builder = new WeatherForecastWithOwmBuilder();
      WeatherForecastRedisValue weatherForecastValue = builder.withForecast(forecast)
              .createWeatherForecast();

      wrapper.setKey(weatherForecastKey);
      wrapper.setValue(weatherForecastValue);

      wrapperList.add(wrapper);

    });

    return wrapperList;
  }
}
