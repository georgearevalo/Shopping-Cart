package orders.exceptions;

import org.springframework.http.HttpStatus;

import orders.exceptions.catalog.OrdersExceptionCatalog;

public class OrdersNotFoundException extends OrdersException {

  public OrdersNotFoundException() {
    super(HttpStatus.NOT_FOUND,
      OrdersExceptionCatalog.ORDERS_NOT_FOUND_ERROR_CODE,
      OrdersExceptionCatalog.ORDERS_NOT_FOUND_ERROR_MESSAGE);
  }

}
