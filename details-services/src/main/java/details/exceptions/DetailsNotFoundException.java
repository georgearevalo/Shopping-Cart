package details.exceptions;

import org.springframework.http.HttpStatus;

import details.exceptions.catalog.DetailsExceptionCatalog;

public class DetailsNotFoundException extends DetailsException {

  public DetailsNotFoundException() {
    super(HttpStatus.NOT_FOUND,
      DetailsExceptionCatalog.ORDERS_NOT_FOUND_ERROR_CODE,
      DetailsExceptionCatalog.ORDERS_NOT_FOUND_ERROR_MESSAGE);
  }

}
