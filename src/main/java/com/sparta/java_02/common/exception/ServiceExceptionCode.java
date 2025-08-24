package com.sparta.java_02.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ServiceExceptionCode {
  // 404 NOT_FOUND
  NOT_FOUND_USER(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),
  NOT_FOUND_PRODUCT(HttpStatus.NOT_FOUND, "존재하지 않는 상품입니다."),
  NOT_FOUND_PURCHASE(HttpStatus.NOT_FOUND, "존재하지 않는 구매입니다."),
  
  // 400 BAD_REQUEST
  INSUFFICIENT_STOCK(HttpStatus.BAD_REQUEST, "재고가 부족합니다."),
  ALREADY_REFUNDED_ORDER(HttpStatus.BAD_REQUEST, "이미 환불된 주문입니다.");

  private final HttpStatus httpStatus;
  private final String message;
}