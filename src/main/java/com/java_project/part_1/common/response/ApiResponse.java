package com.java_project.part_1.common.response;

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

  public static <T> ApiResponse<T> success(T message) {
    return ApiResponse.<T>builder()
        .result(true)
        .message(message)
        .build();
  }

  /**
   * 200대 에러
   */
  public static <T> ResponseEntity<ApiResponse<T>> error(String code, String errorMessage) {
    /**
     * 200으로 발생했지만 의도한 에러를 보낼 때 사용
     * 서비스내에서 발생한 에러를 의도적으로 뱉어낼 때
     */
    return ResponseEntity.ok(ApiResponse.<T>builder()
        .result(false)
        .error(Error.of(code, errorMessage))
        .build());
  }

  /**
   * 사용자 에러 400대 에러
   */
  public static <T> ResponseEntity<ApiResponse<T>> badRequest(String code, String errorMessage) {
    return ResponseEntity.badRequest().body(ApiResponse.<T>builder()
        .result(false)
        .error(Error.of(code, errorMessage))
        .build());
  }

  /**
   * 서버 에러 500대 에러
   */
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
