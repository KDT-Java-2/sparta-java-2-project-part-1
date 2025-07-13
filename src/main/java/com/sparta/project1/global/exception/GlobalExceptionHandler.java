package com.sparta.project1.global.exception;

import com.sparta.project1.common.exception.ServiceException;
import com.sparta.project1.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 실패 예외를 여기서 다 받아처리하는공통으로 수행로직에서는 성공 로직만 넣어줘도 되는 장점
 *
 * @ExceptionHandler 명시된 에러 발생시 로직 수행 APIResponse에 구현된 에러를 가져다 사용하는 역할 단! 컨트롤러에서만 적용하는 컨트롤러 영역
 * 지원범위고, Service단 비즈니스로직에서는 평소처럼 throw new 커스텀Exception 처리 해주면 된다
 */
@Hidden //Swagger 어노테이션으로 아래 로직들이 Swagger 문서화에 안보이게 되며 설정 안할시 Swagger에 포함되어버림
@RestControllerAdvice // 스프링이게 핸들링할것 있다고 통보
public class GlobalExceptionHandler {

    //에러처리 핸들링
    private final String VALIDATE_ERROR = "VALIDATE_ERROR";
    private final String SERVER_ERROR = "SERVER_ERROR";

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> handleResponseException(ServiceException ex) {
        return ApiResponse.error(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        AtomicReference<String> errors = new AtomicReference<>("");
        ex.getBindingResult().getAllErrors().forEach(c -> errors.set(c.getDefaultMessage()));

        return ApiResponse.badRequest(VALIDATE_ERROR, String.valueOf(errors));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> bindException(BindException ex) {
        AtomicReference<String> errors = new AtomicReference<>("");
        ex.getBindingResult().getAllErrors().forEach(c -> errors.set(c.getDefaultMessage()));

        return ApiResponse.badRequest(VALIDATE_ERROR, String.valueOf(errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> serverException(Exception ex) {
        return ApiResponse.serverError(SERVER_ERROR, ex.getMessage());
    }
}
