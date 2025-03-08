package customers;

import customers.configuration.SegurityProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(SegurityProperties.class)
public class CustomersServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(CustomersServiceApplication.class, args);
  }
}
