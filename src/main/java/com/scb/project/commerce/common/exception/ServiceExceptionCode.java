package com.scb.project.commerce.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {

    NOT_FOUND_DATA("데이터를 찾을 수 없습니다."),

    DUPLICATE_EMAIL("이미 사용 중인 이메일입니다."),

    ;

    final String message;
}
