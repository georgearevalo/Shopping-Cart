package details.infraestructure;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import details.configuration.FeignClientConfig;
import details.model.dto.ProductResponse;

@FeignClient(name = "ProductsServicesApiClient", url = "${services.products-services.url}", configuration = FeignClientConfig.class)
public interface ProductsServicesApiClient {

  @GetMapping(value = "/products-services/products/{productId}", produces = { "application/json" })
  ProductResponse getProduct(@PathVariable("productId") Integer productId);
}