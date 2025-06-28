package com.sparta.java_02.global.exception;


import com.sparta.part_1.common.exception.UserServiceException;
import com.sparta.part_1.common.respeonse.ApiResponse;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private final String VALIDATE_ERROR = "VALIDATE_ERROR";
  private final String SERVER_ERROR = "SERVER_ERROR";

  @ExceptionHandler(UserServiceException.class)
  public ResponseEntity<?> handlerResponseException(UserServiceException ex) {
    return ApiResponse.error(ex.getCode(), ex.getMessage());
  }


  // Exception 클래스가 못잡는 두가지 에러
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


  // 혹여나 캐치하지못한 에러를 잡아내는 최상위 Exception에 대한 선언
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> serverException(Exception ex) {
    return ApiResponse.serverError(SERVER_ERROR, ex.getMessage());
  }

}
