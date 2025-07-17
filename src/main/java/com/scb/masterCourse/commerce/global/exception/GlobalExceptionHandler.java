package com.scb.masterCourse.commerce.global.exception;

import com.scb.masterCourse.commerce.common.exception.ServiceException;
import com.scb.masterCourse.commerce.common.response.ApiResponse;
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

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> handleResponseException(ServiceException exception) {
        return ApiResponse.error(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        AtomicReference<String> errors = new AtomicReference<>("");

        exception.getBindingResult()
            .getAllErrors()
            .forEach(c -> errors.set(c.getDefaultMessage()));

        return ApiResponse.badRequest(VALIDATE_ERROR, String.valueOf(errors));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> bindException(BindException exception) {
        AtomicReference<String> errors = new AtomicReference<>("");

        exception.getBindingResult()
            .getAllErrors()
            .forEach(c -> errors.set(c.getDefaultMessage()));

        return ApiResponse.badRequest(VALIDATE_ERROR, String.valueOf(errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> serverException(Exception exception) {
        return ApiResponse.serverError(SERVER_ERROR, exception.getMessage());
    }
}
