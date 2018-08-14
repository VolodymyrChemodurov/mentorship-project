package com.training.weather.ingestor.infrastructure;

import com.training.weather.ingestor.infrastructure.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Class which starts Spring Boot application.
 * @author Oleg_Hudyma
 */
@SpringBootApplication
@Import({ApplicationConfig.class})
@SuppressWarnings("PMD.UseUtilityClass")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
