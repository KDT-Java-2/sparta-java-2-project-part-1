package com.js.commerce.global.exception;

import com.js.commerce.common.exception.ServiceException;
import com.js.commerce.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

  private final String VALIDATE_ERROR = "VALIDATE_ERROR";
  private final String SERVER_ERROR = "SERVER_ERROR";

  @ExceptionHandler(ServiceException.class) // 핸들러 안에 이렇게 메소드를 만들어서 Exception을 캐치하는 용도로 씀
  public ResponseEntity<?> handlerResponseException(ServiceException ex) {
    return ApiResponse.error(ex.getCode(), ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
    AtomicReference<String> errors = new AtomicReference<>("");
    ex.getBindingResult().getAllErrors().forEach(c -> errors.set(c.getDefaultMessage()));

    return ApiResponse.badRequest(VALIDATE_ERROR, String.valueOf(errors));
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<?> bindException(BindException ex) {
    AtomicReference<String> errors = new AtomicReference<>("");
    ex.getBindingResult().getAllErrors().forEach(c -> errors.set(c.getDefaultMessage()));

    return ApiResponse.badRequest(VALIDATE_ERROR, String.valueOf(errors));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> serverException(Exception ex) {
    return ApiResponse.serverError(SERVER_ERROR, ex.getMessage());
  }

}
