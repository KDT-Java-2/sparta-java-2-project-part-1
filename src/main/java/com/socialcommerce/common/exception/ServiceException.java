package com.socialcommerce.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceException extends RuntimeException {

  String code;
  String message;

  public ServiceException(ServiceExceptionCode response) {
    super(response.getMessage()); // super 가 들어간 이유는 ? super 는 상속받은 부모클래스의 생성자를 호출한다. 그리고
    // 예외 메시지(message) 값을 RuntimeException에 넘겨주고 저장한다.
    // 그래서 Exception 처리 로직에서 getMessage() 등으로 기본 메시지 접근이 가능
    this.code = response.name();
    this.message = super.getMessage();
  }

  @Override
  public String getMessage() {
    return message;
  }
}