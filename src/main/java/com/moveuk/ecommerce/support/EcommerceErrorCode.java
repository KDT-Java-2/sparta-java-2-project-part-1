package com.moveuk.ecommerce.support;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum EcommerceErrorCode {

    NOT_FOUND_USER("200", "사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_PRODUCT("200", "상품을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    EcommerceErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
