package com.sparta.java2.project.part1.commerce.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {
    // NOT FOUND!
    NOT_FOUND_USER("유저를 찾을 수 없습니다."),
    NOT_FOUND_PRODUCT("상품을 찾을 수 없습니다."),
    NOT_FOUND_REFUND("환불주문을 찾을 수 없습니다."),
    NOT_FOUND_CATEGORY("상품분류를 찾을 수 없습니다."),

    // Duplicated
    ALREADY_EXIST_EMAIL("이미 존재하는 이메일 입니다."),
    ALREADY_EXIST_PRODUCT_NAME("이미 존재하는 상품명 입니다."),

    // Features
    INSUFFICIENT_STOCK("상품의 재고가 부족합니다."),
    ;

    final String message;
}
