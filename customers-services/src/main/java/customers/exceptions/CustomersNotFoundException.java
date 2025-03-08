package customers.exceptions;

import org.springframework.http.HttpStatus;

import customers.exceptions.catalog.CustomersExceptionCatalog;

public class CustomersNotFoundException extends CustomersException {

  public CustomersNotFoundException() {
    super(HttpStatus.NOT_FOUND,
      CustomersExceptionCatalog.CUSTOMERS_NOT_FOUND_ERROR_CODE,
      CustomersExceptionCatalog.CUSTOMERS_NOT_FOUND_ERROR_MESSAGE);
  }

}
