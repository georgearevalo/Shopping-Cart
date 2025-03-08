package orders.infraestructure;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import orders.configuration.FeignClientConfig;
import orders.model.dto.CustomerResponse;

@FeignClient(name = "CustomerServicesApiClient", url = "${services.customer-services.url}", configuration = FeignClientConfig.class)
public interface CustomerServicesApiClient {

  @GetMapping(value = "/customers-services/customers/{customerId}", produces = { "application/json" })
  CustomerResponse getCustomer(@PathVariable("customerId") Integer customerId);
}