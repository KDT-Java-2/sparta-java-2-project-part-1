package org.example.prj_part_1.global.exception;

import org.example.prj_part_1.common.exception.ServiceException;
import org.example.prj_part_1.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {
  private final String VALIDATE_ERROR = "VALIDATE_ERROR";
  private final String SERVER_ERROR = "SERVER_ERROR";
  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<?> handlerResponseException(ServiceException ex) {
    return ApiResponse.error(ex.getCode(), ex.getMessage());
  }
}
