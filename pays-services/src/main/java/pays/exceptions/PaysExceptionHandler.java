package pays.exceptions;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pays.model.ErrorResponseDTO;

@ControllerAdvice
@Slf4j
public class PaysExceptionHandler {
  @ExceptionHandler({PaysException.class})
  protected ResponseEntity<ErrorResponseDTO> handleSARBOException(PaysException baseException) {
  
    log.error("Pays exception occurred: Http Status Code: {}, Error Code: {}, Error Message: {}",
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
