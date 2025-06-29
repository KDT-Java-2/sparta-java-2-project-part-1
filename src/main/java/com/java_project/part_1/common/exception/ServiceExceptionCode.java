package com.java_project.part_1.common.exception;

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
  NOT_FOUND_PRODUCT("상품을 찾을 수 없습니다."),
  INSUFFICIENT_STOCK("상품의 재고가 부족합니다."),
  EMAIL_ALREADY_EXIST("이미 사용 중인 이메일입니다."),
  NOT_FOUND_CATEGORY("카테고리를 찾을 수 없습니다."),
  EXIST_CHILDREN_CATEGORY("하위 카테고리가 존재합니다."),
  EXIST_CATEGORY_PRODUCT("카테고리의 상품이 존재합니다.");

  final String message;
}
