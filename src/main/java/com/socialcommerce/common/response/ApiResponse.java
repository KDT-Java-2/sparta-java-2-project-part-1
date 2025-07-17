package com.socialcommerce.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;

/* 공통 응답 처리와 예외 핸들링: API의 완성도 높이기 */
/*
 * 모든 응답을 감싸는 제네릭 클래스를 설계합니다.
 * 성공 시에는 data 필드에, 실패 시에는 error 필드에 정보를 담습니다.
 * */
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

  // 200
  public static <T> ApiResponse<T> success(T message) {
    return ApiResponse.<T>builder()
        .result(true)
        .message(message)
        .build();
  }

  // 나머지 기획적 의도를 가지고 나게하는 에러( ex : 200 으로 들어왔지만 result 가 false 일때, ex: 아디중복 등등 .. )
  public static <T> ResponseEntity<ApiResponse<T>> error(String code, String errorMessage) {
    return ResponseEntity.ok(ApiResponse.<T>builder()
        .result(false)
        .error(Error.of(code, errorMessage))
        .build());
  }

  // Controller 로 들어와서 그 주변 에러
  public static <T> ResponseEntity<ApiResponse<T>> badRequest(String code, String errorMessage) {
    return ResponseEntity.badRequest().body(ApiResponse.<T>builder()
        .result(false)
        .error(Error.of(code, errorMessage))
        .build());
  }

  // 서버에서 시스템장애로 인한 Exception Error
  public static <T> ResponseEntity<ApiResponse<T>> serverError(String code, String errorMessage) {
    return ResponseEntity.status(500).body(ApiResponse.<T>builder()
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