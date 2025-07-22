package com.socialcommerce.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {
  NOT_FOUND_PRODUCT("상품을 찾을 수 없습니다."),
  NOT_FOUND_PURCHASE("구매정보를 찾을 수 없습니다."),
  INSUFFICIENT_STOCK("상품의 재고가 부족합니다."),
  NOT_FOUND_USER("유저를 찾을 수 없습니다."),
  NOT_FOUND_DATA("데이터를 찾을 수 없습니다."),
  DUPLICATE_EMAIL("이메일이 중복되었습니다."),
  NOT_EXIST_CATEGORY("해당 카테고리가 존재하지 않습니다."),
  CANNOT_CANCEL("취소 불가능한 상태입니다."),
  OUT_OF_STOCK_PRODUCT("재고 수량을 넘었습니다."),
  FAILED_SOFT_DELETE("비활성화 실패"),
  DUPLICATED_CATEGORY("카테고리 중복"),
  INVALID_PRICE("가격은 음수일 수 없습니다."),
  CANNOT_DELETE_PRODUCT_ALREADY_PURCHASED("구매완료 상품입니다. 삭제할 수 없습니다."),
  DUPLICATED_PRODUCT("등록상품 중복");
  // ... 다른 예외 코드들

  final String message;
}