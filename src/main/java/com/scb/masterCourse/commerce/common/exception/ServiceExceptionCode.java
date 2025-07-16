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

    DUPLICATE_EMAIL("중복된 이메일이 존재합니다."),
    DUPLICATE_NICKNAME("중복된 닉네임이 존재합니다."),
    ;

    final String message;
}
