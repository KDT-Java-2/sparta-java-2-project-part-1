package com.dogworld.dogdog.global.error.exception;

import com.dogworld.dogdog.global.common.response.ApiResponse;
import com.dogworld.dogdog.global.common.response.ErrorResponse;
import com.dogworld.dogdog.global.error.code.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ApiResponse.fail(ErrorResponse.of(errorCode)));
    }
}
