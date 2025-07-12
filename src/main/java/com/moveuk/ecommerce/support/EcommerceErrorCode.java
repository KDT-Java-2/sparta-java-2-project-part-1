package com.moveuk.ecommerce.support;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum EcommerceErrorCode {

    NOT_FOUND_USER("100", "사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_PRODUCT("200", "상품을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    DUPLICATE_PRODUCT_NAME("201", "상품을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    PRODUCT_ALREADY_PURCHASED("202", "이미 주문완료 처리된 상품이 존재합니다.", HttpStatus.INTERNAL_SERVER_ERROR),










    NOT_FOUND_CATEGORY("300", "카테고리를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    CATEGORY_CANNOT_BE_ITS_OWN_PARENT("301","해당 카테고리 아이디의 하위로는 설정할 수 없습니다", HttpStatus.INTERNAL_SERVER_ERROR),
    CATEGORY_CIRCULAR_REFERENCE("302","상위 카테고리로 자기 자신 또는 하위 카테고리를 지정할 수 없습니다.", HttpStatus.BAD_REQUEST),
    CATEGORY_HAS_CHILDREN("303","하위에 설정된 카테고리가 존재합니다.",HttpStatus.INTERNAL_SERVER_ERROR),
    CATEGORY_HAS_PRODUCTS("303","특정 상품에 해당 카테고리가 존재합니다.",HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    EcommerceErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
