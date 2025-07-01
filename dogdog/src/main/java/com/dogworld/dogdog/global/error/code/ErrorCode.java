package com.dogworld.dogdog.global.error.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND_USER("NOT_FOUND_USER", "존재하지 않는 멤버입니다.", HttpStatus.NOT_FOUND),
    DUPLICATED_EMAIL("DUPLICATED_EMAIL", "이미 사용 중인 이메일입니다.", HttpStatus.CONFLICT),
    DUPLICATED_USERNAME("DUPLICATED_USERNAME", "이미 사용 중인 아이디입니다.", HttpStatus.CONFLICT),


    //category
    NOT_FOUND_CATEGORY("NOT_FOUND_CATEGORY", "카테고리를 찾을 수 없습니다.", HttpStatus.NOT_FOUND)
    ;


    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

}
