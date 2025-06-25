package com.moveuk.ecommerce.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(ApiControllerAdvice.class);

    @ExceptionHandler(EcommerceException.class)
    public ResponseEntity<ApiResponse<Void>> handleHangHeaException(EcommerceException e) {

        log.error("Handled EcommerceException - Code: {}, Message: {}, HTTP Status: {}",
                e.getCode(), e.getMessage(), e.getHttpStatus(), e);

        ResponseEntity<ApiResponse<Void>> response = ApiResponse.error(e);
        return ResponseEntity.status(e.getHttpStatus()).body(response.getBody());
    }
}
