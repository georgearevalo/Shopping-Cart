package pays.exceptions;

import org.springframework.http.HttpStatus;

import pays.exceptions.catalog.PaysExceptionCatalog;

public class OrderNotFoundException extends PaysException {

  public OrderNotFoundException() {
    super(HttpStatus.NOT_FOUND,
      PaysExceptionCatalog.ORDERS_NOT_FOUND_ERROR_CODE,
      PaysExceptionCatalog.ORDERS_NOT_FOUND_ERROR_MESSAGE);
  }

}
