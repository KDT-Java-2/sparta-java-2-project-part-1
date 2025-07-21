package com.sparta.e_project.common.response;

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
  T message;
  Error error;

  public static <T> ApiResponse<T> success() {
    return success(null);
  }

  public static <T> ApiResponse<T> success(T message) {
    return ApiResponse.<T>builder()
        .result(true)
        .message(message)
        .build();
  }

  //자바시스템 에러가 아니라 우리간 의도한 에러 메세지를 내보내서
  public static ResponseEntity<ApiResponse<String>> error(String code, String errorMessage) {
    return ResponseEntity.ok(ApiResponse.<String>builder()
        .result(false)
        .error(Error.of(code, errorMessage))
        .build());
  }
// 위에는 200, 아래는 400, 500 에러

  public static ResponseEntity<ApiResponse<String>> badRequest(String code, String errorMessage) {
    return ResponseEntity.badRequest().body(ApiResponse.<String>builder()
        .result(false)
        .error(Error.of(code, errorMessage))
        .build());
  }

  public static ResponseEntity<ApiResponse<String>> serverError(String code, String errorMessage) {
    return ResponseEntity.status(500).body(ApiResponse.<String>builder()
        .result(false)
        .error(Error.of(code, errorMessage))
        .build());
  }

  @JsonInclude(JsonInclude.Include.NON_NULL)
  public record Error(String errorCode, String errorMessage) {

    public static Error of(String errorCode, String errorMessage) {
      return new Error(errorCode, errorMessage);
    }
  }


}
