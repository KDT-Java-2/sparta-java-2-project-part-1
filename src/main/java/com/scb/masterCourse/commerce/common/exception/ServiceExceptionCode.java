package com.scb.masterCourse.commerce.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {

    NOT_FOUND_USER("사용자를 찾을 수 없습니다."),
    NOT_FOUND_PRODUCT("상품을 찾을 수 없습니다."),
    NOT_FOUND_BRAND("브랜드를 찾을 수 없습니다."),
    NOT_FOUND_CATEGORY("카테고리를 찾을 수 없습니다."),

    DUPLICATE_EMAIL("중복된 이메일이 존재합니다."),
    DUPLICATE_NICKNAME("중복된 닉네임이 존재합니다."),
    DUPLICATE_PRODUCT_NAME("중복된 상품명이 존재합니다."),

    CANNOT_DELETE_COMPLETED_PRODUCT("결제 완료된 주문은 삭제할 수 없습니다."),
    ;

    final String message;
}
