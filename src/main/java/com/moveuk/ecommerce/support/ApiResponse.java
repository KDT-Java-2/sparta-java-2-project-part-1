package com.moveuk.ecommerce.support;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ApiResponse<T> {

    private  String resultCode;
    private  String status;
    private  String message;
    private  Boolean result;
    private  EcommerceErrorCode error;
    private  T data;

    public static <T> ApiResponse<T> success() {
        return success(null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .result(true)
                .message("요청이 정상 처리되었습니다.")
                .data(data)
                .build();
    }


    private ApiResponse(String resultCode, String status, String message, Boolean result, EcommerceErrorCode error, T data) {
        this.resultCode = resultCode;
        this.status = status;
        this.message = message;
        this.result = result;
        this.error = error;
        this.data = data;
    }

    public static <T> ResponseEntity<ApiResponse<T>> error(EcommerceException e) {
        return ResponseEntity.ok(ApiResponse.<T>builder()
                .result(false)
                .error(e.getCode())
                .build());
    }

}
