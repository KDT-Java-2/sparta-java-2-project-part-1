package com.dogworld.dogdog.global.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private final boolean result;
    private final T message;
    private final ErrorResponse error;

    @Builder
    public ApiResponse(boolean result, T message, ErrorResponse error) {
        this.result = result;
        this.message = message;
        this.error = error;
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(true, null, null);
    }

    public static <T> ApiResponse<T> success(T message) {
        return new ApiResponse<>(true, message, null);
    }

    public static <T> ApiResponse<T> fail(ErrorResponse error) {
        return new ApiResponse<>(false, null, error);
    }

    public static <T> ApiResponse<T> fail(ErrorResponse error, T message) {
        return new ApiResponse<>(false, message, error);
    }
}
