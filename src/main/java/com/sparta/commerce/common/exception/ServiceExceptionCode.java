package com.sparta.commerce.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {
  NOT_FOUND_DATA("데이터를 찾을 수 없습니다."),
  NOT_FOUND_USER("사용자를 찾을 수 없습니다."),
  INSUFFICIENT_STOCK("재고가 부족합니다."),
  EMAIL_ALREADY_EXISTS("이미 사용 중인 이메일입니다."),
  ;

  final String messsage;
}