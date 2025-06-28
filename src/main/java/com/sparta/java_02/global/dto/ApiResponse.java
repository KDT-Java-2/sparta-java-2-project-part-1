package com.sparta.java_02.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean result;
    private T message;
    private Object error;

    // 성공 응답
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, new Object());
    }

    // 실패 응답
    public static <T> ApiResponse<T> error(String errorMessage) {
        return new ApiResponse<>(false, null, errorMessage);
    }

    // 실패 응답 (에러 객체)
    public static <T> ApiResponse<T> error(Object errorObj) {
        return new ApiResponse<>(false, null, errorObj);
    }
} 