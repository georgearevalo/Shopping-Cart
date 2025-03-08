package pays;

import pays.configuration.SegurityProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(SegurityProperties.class)
public class PaysServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(PaysServiceApplication.class, args);
  }
}
