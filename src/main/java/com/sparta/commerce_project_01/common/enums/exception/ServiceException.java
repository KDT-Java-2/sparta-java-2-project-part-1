package com.sparta.commerce_project_01.common.enums.exception;

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

  public ServiceException(ServiceExceptionCode code) {
    super(code.getMessage());
    this.code = code.name();
    this.message = super.getMessage();
  }

  @Override
  public String getMessage() {
    return this.message;
  }
}
