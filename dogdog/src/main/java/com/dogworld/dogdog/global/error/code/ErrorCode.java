package com.dogworld.dogdog.global.error.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NO_FOUND_USER("NO_FOUND_USER", "존재하지 않는 멤버입니다.", HttpStatus.NOT_FOUND)
    ;


    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

}
