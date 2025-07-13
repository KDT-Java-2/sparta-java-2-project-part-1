package com.sparta.commerce.global.exception;

import com.sparta.commerce.common.exception.ServiceException;
import com.sparta.commerce.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<?> handleException(ServiceException ex) {
    return ApiResponse.error(ex.getCode(), ex.getMessage());
  }

}
