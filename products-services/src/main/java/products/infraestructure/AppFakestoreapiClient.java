package products.infraestructure;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import products.model.dto.Product;

@FeignClient(name = "AppFakestoreapiClient", url = "${external-services.fakestoreapi.url}")
public interface AppFakestoreapiClient {

  @GetMapping(value = "/products", produces = { "application/json" })
  List<Product> getAllProducts();
}
