package com.sparta.java_02.common.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

  private final ServiceExceptionCode exceptionCode;

  public ServiceException(ServiceExceptionCode exceptionCode) {
    super(exceptionCode.getMessage());
    this.exceptionCode = exceptionCode;
  }
}