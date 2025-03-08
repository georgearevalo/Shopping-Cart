package customers.controller;

import customers.model.*;
import customers.service.CustomersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CustomersServicesController implements CustomersServicesApi {

  private final CustomersService customersService;

  @Override
  public ResponseEntity<CustomerResponse> getCustomer(
    @Parameter(name = "CustomerId", description = "Customer Id", required = true, in = ParameterIn.PATH) @PathVariable("CustomerId") Integer customerId) {
    log.info("getCustomers method called");
    log.info("CustomerId: {}", customerId);
    
    return new ResponseEntity<>(customersService.getCustomer(customerId), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CustomerResponse> saveCustomer(CustomerResponse customer) {
    log.info("saveCustomer method called");
    log.info("customer: {}", customer.toString());
    
    return new ResponseEntity<>(customersService.saveCustomer(customer), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> updateCustomer(CustomerResponse customer) {
    log.info("updateCustomer method called");
    log.info("Customer: {}", customer.toString());
    
    customersService.updateCustomer(customer);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }

  @Override
  public ResponseEntity<Void> deleteCustomer(
    @Parameter(name = "CustomerId", description = "Customer Id", required = true, in = ParameterIn.PATH) @PathVariable("CustomerId") Integer customerId) {
    log.info("deleteCustomer method called");
    log.info("CustomerId: {}", customerId);
    
    customersService.deleteCustomer(customerId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }
}
