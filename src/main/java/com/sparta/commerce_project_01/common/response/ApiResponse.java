package com.sparta.commerce_project_01.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse<T> {

  Boolean result;
  Error error;
  T message;

  public static <T> ApiResponse<T> success() {
    return success(null);
  }

  // ApiResponse.success(), ResponseEntity 안거친다??
  public static <T> ApiResponse<T> success(T message) {
    return ApiResponse.<T>builder()
        .result(true)
        .message(message)
        .build();
  }

  // 서버 로직내에서 개발자가 의도적으로 정의된 에러가 발생할 경우 사용함(200에 result false, 오류 메세지 포함시켜서 응답)
  public static <T> ResponseEntity<ApiResponse<T>> error(String code, String errorMessage) {
    return ResponseEntity.ok(ApiResponse.<T>builder()
        .result(false)
        .error(Error.of(code, errorMessage))
        .build());
  }

  // emergency  상황
  public static <T> ResponseEntity<ApiResponse<T>> badRequest(String code, String errorMessage) {
    return ResponseEntity.badRequest().body(ApiResponse.<T>builder()
        .result(false)
        .error(Error.of(code, errorMessage))
        .build());
  }

  // emergency  상황
  public static <T> ResponseEntity<ApiResponse<T>> serverError(String code, String errorMessage) {
    return ResponseEntity.status(500).body(ApiResponse.<T>builder()
        .result(false)
        .error(Error.of(code, errorMessage))
        .build());
  }

  // ID 중복인 경우에 처리
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public record Error(String errorCode, String errorMessage) {

    public static Error of(String errorCode, String errorMessage) {
      return new Error(errorCode, errorMessage);
    }
  }
}