package products;

import products.configuration.ProductsServicesProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(ProductsServicesProperties.class)
public class ProductsServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(ProductsServiceApplication.class, args);
  }
}
