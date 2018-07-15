import com.training.weather.ingestor.infrastructure.config.ApplicationConfig;
import com.training.weather.ingestor.infrastructure.service.OpenWeatherMapDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApplicationConfig.class})
public class SpringIntegrationTest {

  @Autowired
  private OpenWeatherMapDataSource openWeatherMapDataSource;

  @Test
  public void test() {
    //TODO
  }
}
