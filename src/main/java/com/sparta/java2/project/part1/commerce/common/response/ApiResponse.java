package com.sparta.java2.project.part1.commerce.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
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

    public static <T> ResponseEntity<ApiResponse<T>> error(String code, String errorMessage) {
        return ResponseEntity.ok(ApiResponse.<T>builder()
                .result(false)
                .error(Error.of(code, errorMessage))
                .build());
    }

    public static <T> ResponseEntity<ApiResponse<T>> badRequest(String code, String errorMessage) {
        return ResponseEntity.badRequest().body(ApiResponse.<T>builder()
                .result(false)
                .error(Error.of(code, errorMessage))
                .build());
    }

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
