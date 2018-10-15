package com.training.weather.api.infrastructure.web;

import com.training.weather.api.infrastructure.web.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Class which starts Spring Boot application.
 *
 * @author Oleg_Hudyma
 */
@SpringBootApplication
@Import({ApplicationConfig.class})
@SuppressWarnings("PMD.UseUtilityClass")
public class Application {

  /**
   * Builds and runs Spring Boot Application.
   *
   * @param args Java command line args.
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }
}
