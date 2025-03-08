package customers.service;

import customers.exceptions.CustomersNotFoundException;
import customers.mapper.CustomerMapper;
import customers.model.CustomerResponse;
import customers.model.dto.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomersServiceTest {

  @Mock
  private CustomerMapper customerMapper;

  @InjectMocks
  private CustomersService customersService;

  private Customer customer;
  private CustomerResponse customerResponse;

  @BeforeEach
  void setUp() {
    customer = new Customer();
    customer.setId(1);
    customer.setName("Customer number 1");
    customer.setEmail("customer1@company.com");
    customer.setAddress("City four");

    customerResponse = new CustomerResponse();
    customerResponse.setCustomerId(1);
    customerResponse.setCustomerName("Customer number 1");
    customerResponse.setCustomerEmail("customer1@company.com");
    customerResponse.setCustomerAddress("City four");
  }

  @Test
  void getCustomer_existingCustomer_returnsCustomerResponse() {
    when(customerMapper.customerToCustomerResponse(customer)).thenReturn(customerResponse);

    CustomerResponse response = customersService.getCustomer(1);

    assertEquals(customerResponse, response);
  }

  @Test
  void getCustomer_nonExistingCustomer_throwsCustomersNotFoundException() {
    assertThrows(CustomersNotFoundException.class, () -> customersService.getCustomer(2));
  }

  @Test
  void saveCustomer_anyCustomer_returnsCustomerResponseWithId2() {
    CustomerResponse inputCustomer = new CustomerResponse();
    CustomerResponse result = customersService.saveCustomer(inputCustomer);

    assertEquals(2, result.getCustomerId());
  }

  @Test
  void updateCustomer_existingCustomer_doesNotThrowException() {
    CustomerResponse customerResponse = new CustomerResponse();
    customerResponse.setCustomerId(1);
    when(customerMapper.customerResponseToCustomer(customerResponse)).thenReturn(customer);
    assertDoesNotThrow(() -> customersService.updateCustomer(customerResponse));
  }

  @Test
  void updateCustomer_nonExistingCustomer_throwsCustomersNotFoundException() {
    CustomerResponse customerResponse = new CustomerResponse();
    customerResponse.setCustomerId(2);
    assertThrows(CustomersNotFoundException.class, () -> customersService.updateCustomer(customerResponse));
  }

  @Test
    void deleteCustomer_existingCustomer_doesNotThrowException() {
    assertDoesNotThrow(() -> customersService.deleteCustomer(1));
  }

  @Test
  void deleteCustomer_nonExistingCustomer_throwsCustomersNotFoundException() {
    assertThrows(CustomersNotFoundException.class, () -> customersService.deleteCustomer(2));
  }
}