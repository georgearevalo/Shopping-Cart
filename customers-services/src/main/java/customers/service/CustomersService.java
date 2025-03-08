package customers.service;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import customers.exceptions.CustomersNotFoundException;
import customers.mapper.CustomerMapper;
import customers.model.CustomerResponse;
import customers.model.dto.Customer;

/**
 * The {@code CustomersService} class provides the business logic for managing customer-related
 * operations. It includes methods for retrieving, saving, updating, and deleting customer
 * information.
 *
 * <p>This service interacts with the {@link CustomerMapper} to map between {@link Customer}
 * entities and {@link CustomerResponse} DTOs. It also uses a hardcoded {@link #getInfoCustomer()}
 * method to simulate retrieving customer data.
 *
 * <p>The service methods throw {@link CustomersNotFoundException} when a customer with a given ID
 * is not found.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomersService {

  private final CustomerMapper customerMapper;

  /**
   * Retrieves a customer's information based on their ID.
   *
   * @param customerId The ID of the customer to retrieve.
   * @return A {@link CustomerResponse} object containing the customer's information.
   * @throws CustomersNotFoundException if no customer is found with the given ID.
   */
  public CustomerResponse getCustomer(int customerId) {
    CustomerResponse response = new CustomerResponse();
    Customer customer = getInfoCustomer();
    
    if (customer.getId() == customerId) {
      response = customerMapper.customerToCustomerResponse(customer);
    } else {
      throw new CustomersNotFoundException();
    }
    return response;
  }

  /**
   * Saves a customer.
   *
   * @param customer The customer to save.
   * @return The saved customer.
   */
  public CustomerResponse saveCustomer(CustomerResponse customer) {
    customer.setCustomerId(2);
    return customer;
  }

  /**
   * Updates a customer's information based on the provided customer ID.
   *
   * @param customerId The ID of the customer to update.
   * @throws CustomersNotFoundException if the customer with the given ID is not found.
   */
  public void updateCustomer(CustomerResponse customer) {
    Customer customerExist = getInfoCustomer();
    
    if (customerExist.getId() == customer.getCustomerId()) {
      customerExist = customerMapper.customerResponseToCustomer(customer);
    } else {
      throw new CustomersNotFoundException();
    }
  }

  /**
    * Deletes a customer based on the provided customer ID.
    *
    * @param customerId The ID of the customer to delete.
    * @throws CustomersNotFoundException if the customer with the given ID is not found.
    */
  public void deleteCustomer(Integer customerId) {
    CustomerResponse response = new CustomerResponse();
    Customer customer = getInfoCustomer();
    
    if (customer.getId() == customerId) {
      response = customerMapper.customerToCustomerResponse(customer);
    } else {
      throw new CustomersNotFoundException();
    }
  }

  /**
   * Retrieves customer information.
   *
   * @return a Customer object with predefined information.
   */
  protected Customer getInfoCustomer() {
    Customer customer = new Customer();
    customer.setId(1);
    customer.setName("Customer number 1");
    customer.setEmail("customer1@company.com");
    customer.setAddress("City four");
    return customer;
  }
}
