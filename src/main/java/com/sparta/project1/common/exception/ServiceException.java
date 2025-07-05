package com.sparta.project1.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceException extends RuntimeException {

    String code;
    String message;

    public ServiceException(ServiceExceptionCode code) {
        //부모클래스(RuntimeException) 생성자를 호출하며 해당 클래스는 문자열 파라미터를 요구하여 code.getMessage() 넣음
        super(code.getMessage());
        this.code = code.name();
        this.message = super.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

}