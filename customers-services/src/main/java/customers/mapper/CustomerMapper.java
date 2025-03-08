package customers.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import customers.model.dto.Customer;
import customers.model.CustomerResponse;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
  
  @Mapping(target = "customerId", source = "id")
  @Mapping(target = "customerName", source = "name")
  @Mapping(target = "customerEmail", source = "email")
  @Mapping(target = "customerAddress", source = "address")
  CustomerResponse customerToCustomerResponse(Customer customer);

  @Mapping(target = "id", source = "customerId")
  @Mapping(target = "name", source = "customerName")
  @Mapping(target = "email", source = "customerEmail")
  @Mapping(target = "address", source = "customerAddress")
  Customer customerResponseToCustomer(CustomerResponse customer);

  List<CustomerResponse> toDTOList(List<Customer> customersList);

}
