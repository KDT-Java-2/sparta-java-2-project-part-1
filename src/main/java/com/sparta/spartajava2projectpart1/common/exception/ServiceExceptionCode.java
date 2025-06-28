package com.sparta.spartajava2projectpart1.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {
    NOT_FOUND_DATA("데이터를 찾을 수 없습니다."),
    NOT_FOUND_USER("사용자를 찾을 수 없습니다."),
    USER_ALREADY_EXIST("이미 존재하는 사용자 입니다."),
    ;
    final String message;
}
