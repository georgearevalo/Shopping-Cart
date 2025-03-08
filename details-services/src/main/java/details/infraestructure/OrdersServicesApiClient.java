package details.infraestructure;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import details.configuration.FeignClientConfig;
import details.model.dto.OrderResponse;

@FeignClient(name = "OrdersServicesApiClient", url = "${services.orders-services.url}", configuration = FeignClientConfig.class)
public interface OrdersServicesApiClient {

  @GetMapping(value = "/orders-services/orders/{orderId}", produces = { "application/json" })
  OrderResponse getOrder(@PathVariable("orderId") Integer orderId);
}