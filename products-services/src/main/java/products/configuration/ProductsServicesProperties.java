package products.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Primary
@Component
@ConfigurationProperties(prefix = "external-services")
public class ProductsServicesProperties {

  private String fakestoreapi;
}
