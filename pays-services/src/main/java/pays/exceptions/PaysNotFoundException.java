package pays.exceptions;

import org.springframework.http.HttpStatus;

import pays.exceptions.catalog.PaysExceptionCatalog;

public class PaysNotFoundException extends PaysException {

  public PaysNotFoundException() {
    super(HttpStatus.NOT_FOUND,
      PaysExceptionCatalog.PAYS_NOT_FOUND_ERROR_CODE,
      PaysExceptionCatalog.PAYS_NOT_FOUND_ERROR_MESSAGE);
  }

}
