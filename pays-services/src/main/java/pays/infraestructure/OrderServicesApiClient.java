package pays.infraestructure;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pays.configuration.FeignClientConfig;
import pays.model.dto.OrderResponse;

@FeignClient(name = "OrderServicesApiClient", url = "${services.orders-services.url}", configuration = FeignClientConfig.class)
public interface OrderServicesApiClient {

  @GetMapping(value = "/orders-services/orders/{orderId}", produces = { "application/json" })
  OrderResponse getOrder(@PathVariable("orderId") Integer orderId);
}