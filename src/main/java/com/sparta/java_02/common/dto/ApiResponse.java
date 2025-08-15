package com.sparta.java_02.common.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

  private int status;
  private T data;
  private String message;
  private LocalDateTime timestamp;

  public static <T> ApiResponse<T> success(T data) {
    return ApiResponse.<T>builder()
        .status(HttpStatus.OK.value())
        .data(data)
        .message("요청이 성공적으로 처리되었습니다.")
        .timestamp(LocalDateTime.now())
        .build();
  }

  public static <T> ApiResponse<T> success(HttpStatus httpStatus, T data) {
    return ApiResponse.<T>builder()
        .status(httpStatus.value())
        .data(data)
        .message("요청이 성공적으로 처리되었습니다.")
        .timestamp(LocalDateTime.now())
        .build();
  }

  public static ApiResponse<?> failure(HttpStatus httpStatus, String message) {
    return ApiResponse.builder()
        .status(httpStatus.value())
        .data(null)
        .message(message)
        .timestamp(LocalDateTime.now())
        .build();
  }

}
