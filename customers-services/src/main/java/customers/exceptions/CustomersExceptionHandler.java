package customers.exceptions;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import customers.model.ErrorResponseDTO;

@ControllerAdvice
@Slf4j
public class CustomersExceptionHandler {
  @ExceptionHandler({CustomersException.class})
  protected ResponseEntity<ErrorResponseDTO> handleSARBOException(CustomersException baseException) {
  
    log.error("Customers exception occurred: Http Status Code: {}, Error Code: {}, Error Message: {}",
      baseException.getStatusCode().value(),
      baseException.getErrorCode(),
      baseException.getMessage());
  
    ErrorResponseDTO error = new ErrorResponseDTO();
    error.setStatusCode(baseException.getStatusCode().value());
    error.setErrorCode(baseException.getErrorCode());
    error.setTimestamp(LocalDateTime.now());
    error.setMessage(baseException.getMessage());
    return new ResponseEntity<>(error, baseException.getStatusCode());
  }
}
