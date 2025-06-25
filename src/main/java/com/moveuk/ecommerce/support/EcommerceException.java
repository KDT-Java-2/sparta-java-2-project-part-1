package com.moveuk.ecommerce.support;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EcommerceException extends RuntimeException{

    EcommerceErrorCode code;
    String message;
    HttpStatus httpStatus;

    public EcommerceException(EcommerceErrorCode ecommerceErrorCode) {
        super(ecommerceErrorCode.getMessage());
        this.code = ecommerceErrorCode;
        this.message = ecommerceErrorCode.getMessage();
        this.httpStatus = ecommerceErrorCode.getHttpStatus();

    }




}
