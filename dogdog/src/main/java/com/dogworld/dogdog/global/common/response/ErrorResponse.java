package com.dogworld.dogdog.global.common.response;

import com.dogworld.dogdog.global.error.code.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Map;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final String code;
    private final String message;

    @JsonInclude(Include.NON_NULL)
    private Map<String, String> validationErrors;

    public ErrorResponse(String code, String message) {
        this(code, message, null);
    }

    public ErrorResponse(String code, String message, Map<String, String> validationErrors) {
        this.code = code;
        this.message = message;
        this.validationErrors = validationErrors;
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getCode(), errorCode.getMessage());
    }

    public static ErrorResponse of(String code, String message) {
        return new ErrorResponse(code, message);
    }

    public static ErrorResponse of(ErrorCode errorCode, Map<String, String> validationErrors) {
        return new ErrorResponse(errorCode.getCode(), errorCode.getMessage(), validationErrors);
    }
}
