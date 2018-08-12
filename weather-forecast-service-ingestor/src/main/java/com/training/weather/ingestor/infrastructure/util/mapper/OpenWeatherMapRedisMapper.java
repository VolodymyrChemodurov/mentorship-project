package com.training.weather.ingestor.infrastructure.util.mapper;

import com.training.weather.ingestor.core.model.WeatherForecastKeyValueWrapper;
import com.training.weather.ingestor.core.model.WeatherForecastRedisKey;
import com.training.weather.ingestor.core.model.WeatherForecastRedisValue;
import com.training.weather.ingestor.core.model.WeatherForecastRedisWrapper;
import com.training.weather.ingestor.core.model.WeatherForecastWrapper;
import com.training.weather.ingestor.core.util.Mapper;
import com.training.weather.ingestor.infrastructure.model.owm.OpenWeatherMapResponse;
import com.training.weather.ingestor.infrastructure.model.owm.WeatherForecastBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("OpenWeatherMapRedisMapper")
public class OpenWeatherMapRedisMapper<T extends OpenWeatherMapResponse> implements Mapper<T> {

  @Override
  public List<WeatherForecastWrapper> map(T object) {
    List<WeatherForecastWrapper> wrapperList = new ArrayList<>();

    object.getForecasts().forEach(forecast -> {
      WeatherForecastKeyValueWrapper<WeatherForecastRedisKey, WeatherForecastRedisValue> wrapper
              = new WeatherForecastRedisWrapper<>();

      WeatherForecastRedisKey weatherForecastKey = new WeatherForecastRedisKey();
      weatherForecastKey.setTimestamp(forecast.getTimestamp());
      weatherForecastKey.setCoordinates(object.getCoordinates());

      WeatherForecastBuilder builder = new WeatherForecastBuilder();
      WeatherForecastRedisValue weatherForecastValue = builder.withForecast(forecast)
              .createWeatherForecast();

      wrapper.setKey(weatherForecastKey);
      wrapper.setValue(weatherForecastValue);

      wrapperList.add(wrapper);

    });

    return wrapperList;
  }
}
