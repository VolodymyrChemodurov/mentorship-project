package com.training.weather.ingestor.infrastructure;

import com.training.weather.ingestor.infrastructure.config.ApplicationConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
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
    new SpringApplicationBuilder(Application.class)
            .properties(getPropertiesFileName(args[0]))
            .build()
            .run(args);
  }

  /**
   * Used for building Spring property for configuring properties file name.
   *
   * @param args Java command line arguments.
   * @return String. Spring property for configuring properties file name.
   */
  private static String getPropertiesFileName(String args) {
    String propertiesFileName = "application-" + args;
    String propertiesFileKey = "spring.config.name";

    return propertiesFileKey + ":" + propertiesFileName;
  }
}
