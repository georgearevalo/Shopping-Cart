package pays.exceptions;

import org.springframework.http.HttpStatus;

import pays.exceptions.catalog.PaysExceptionCatalog;

public class TotalIncorrectException extends PaysException {

  public TotalIncorrectException() {
    super(HttpStatus.NOT_FOUND,
      PaysExceptionCatalog.TOTAL_INCORRECT_ERROR_CODE,
      PaysExceptionCatalog.TOTAL_INCORRECT_ERROR_MESSAGE);
  }

}
