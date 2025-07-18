package com.socialcommerce.global.exception;

import com.socialcommerce.common.exception.ServiceException;
import com.socialcommerce.common.response.ApiResponse;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 내부적으로 ExceptionHandlerExceptionResolver 라는 컴포넌트가 이 클래스를 추적하고 캐싱함
// 미리 등록해두는것임.

// 애플리케이션 실행 시 @ControllerAdvice → 스프링이 스캔해서 예외 핸들러로 등록
// 요청 처리 중 예외 발생
// ExceptionHandlerExceptionResolver 가 예외를 감지
// 등록된 @ExceptionHandler 메서드 중 가장 적절한 걸 찾아 실행
// 그 메서드의 ResponseEntity 결과가 클라이언트로 응답됨
@RestControllerAdvice
public class GlobalExceptionHandler {

  private final String VALIDATE_ERROR = "VALIDATE_ERROR";
  private final String SERVER_ERROR = "SERVER_ERROR";

  // 내가 정의해준 Global Error 핸들러
  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<?> handleResponseException(ServiceException ex) {
    return ApiResponse.error(ex.getCode(), ex.getMessage());
  }

  // 웹에서는 Request/Response 로 넘어오기전에 필터가있는데 이는 Spring 에서는 잡지 못한다.
  // 그때 잡을수있는 에러 핸들러이다
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
    AtomicReference<String> errors = new AtomicReference<>("");
    ex.getBindingResult().getAllErrors().forEach(c -> errors.set(c.getDefaultMessage()));

    return ApiResponse.badRequest(VALIDATE_ERROR, String.valueOf(errors));
  }

  // 웹에서는 Request/Response 로 넘어오기전에 필터가있는데 이는 Spring 에서는 잡지 못한다.
  // 그때 잡을수있는 에러 핸들러이다
  @ExceptionHandler(BindException.class)
  public ResponseEntity<?> bindException(BindException ex) {
    AtomicReference<String> errors = new AtomicReference<>("");
    ex.getBindingResult().getAllErrors().forEach(c -> errors.set(c.getDefaultMessage()));

    return ApiResponse.badRequest(VALIDATE_ERROR, String.valueOf(errors));
  }

  // Controller 로 넘어오고나서의 서버에러
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> serverException(Exception ex) {
    return ApiResponse.serverError(SERVER_ERROR, ex.getMessage());
  }
}
