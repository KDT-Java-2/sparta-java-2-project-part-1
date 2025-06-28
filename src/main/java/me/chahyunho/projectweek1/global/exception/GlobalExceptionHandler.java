package me.chahyunho.projectweek1.global.exception;


import io.swagger.v3.oas.annotations.Hidden;
import java.util.concurrent.atomic.AtomicReference;
import lombok.extern.log4j.Log4j2;
import me.chahyunho.projectweek1.common.enums.exception.ServiceException;
import me.chahyunho.projectweek1.common.enums.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

  private final String VALIDATE_ERROR = "VALIDATE_ERROR";
  private final String SERVER_ERROR = "SERVER_ERROR";

  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<?> handleResponseException(ServiceException ex) {
    logToErrorStackTrace(ex);
    return ApiResponse.error(ex.getCode(), ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
    logToErrorStackTrace(ex);
    AtomicReference<String> errors = new AtomicReference<>("");
    ex.getBindingResult().getAllErrors().forEach(c -> errors.set(c.getDefaultMessage()));
    return ApiResponse.badRequest(VALIDATE_ERROR, String.valueOf(errors));
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<?> bindException(BindException ex) {
    logToErrorStackTrace(ex);
    AtomicReference<String> errors = new AtomicReference<>("");
    ex.getBindingResult().getAllErrors().forEach(c -> errors.set(c.getDefaultMessage()));

    return ApiResponse.badRequest(VALIDATE_ERROR, String.valueOf(errors));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> serverException(Exception ex) {
    logToErrorStackTrace(ex);
    return ApiResponse.serverError(SERVER_ERROR, ex.getMessage());
  }

  private void logToErrorStackTrace(Exception ex) {
    if (null != ex.getStackTrace()) {
      StringBuilder sb = new StringBuilder();
      for (StackTraceElement element : ex.getStackTrace()) {
        sb.append(element.toString()).append("\n");
      }
      log.error("StackTrace : {}\n{}", ex.getMessage(), sb.toString());
    }
  }
}
