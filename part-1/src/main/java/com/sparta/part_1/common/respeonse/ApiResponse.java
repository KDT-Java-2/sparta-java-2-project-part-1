package com.sparta.part_1.common.respeonse;


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

  // success 함수들은 ResponseEntity를 사용하지 않아도 상태코드가 200으로 가기때문에 따로 감싸지 않는다.
  public static <T> ApiResponse<T> success() {
    return success(null);
  }

  public static <T> ApiResponse<T> success(T message) {
    return ApiResponse.<T>builder()
        .result(true)
        .message(message)
        .build();
  }

  public static <T> ResponseEntity<ApiResponse<T>> error(String code,
      String errorMessage) { // 비즈니스 로직에서 의도적으로 발생한 Exception
    return ResponseEntity.ok(ApiResponse.<T>builder()
        .result(false)
        .error(Error.of(code, errorMessage))
        .build());
  }

  public static <T> ResponseEntity<ApiResponse<T>> badRequest(String code,
      String errorMessage) { // 사용자 요청 관련 에러, 컨트롤러에 닿지 못하는 에러(400, 404,405)
    return ResponseEntity.badRequest().body(ApiResponse.<T>builder()
        .result(false)
        .error(Error.of(code, errorMessage))
        .build());
  }

  public static <T> ResponseEntity<ApiResponse<T>> serverError(String code, String errorMessage) {
    // 치명적 에러, Java 로직상 백엔드에서 잘못 발생된 에러
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
