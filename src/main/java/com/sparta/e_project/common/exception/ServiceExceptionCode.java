package com.sparta.e_project.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {

  NOT_FOUND_PRODUCT("상품을 찾을 수 없습니다."),
  INSUFFICIENT_STOCK("상품의 재고가 부족합니다."),
  NOT_FOUND_USER("유저를 찾을 수 없습니다."),
  DUPLICATE_EMAIL("이미 사용 중인 이메일입니다."),
  NOT_FOUND_CATEGORY("카테고리를 찾을 수 없습니다."),
  NEGATIVE_NUMBER("음수값은 허용되지 않습니다."),
  DUPLICATE_PRODUCT_NAME("이미 존재하는 상품명입니다."),
  EXIST_COMPLETED_PRODUCT("주문 상태가 완료 상태입니다."),
  DUPLICATE_CATEGORY_NAME("이미 존재하는 카테고리명입니다."),
  CATEGORY_MYSELF("내 자신을 부모 카테고리로 지정할 수 없습니다."),
  NOT_FOUND_PARENT_CATEGORY("부모 카테고리를 찾을 수 없습니다."),
  CIRCULAR_REFERENCE("순환 참조는 허용하지 않습니다."),
  CATEGORY_HAS_SUBCATEGORIES("하위 카테고리가 존재하여 삭제할 수 없습니다."),
  CATEGORY_HAS_PRODUCTS("카테고리에 속한 상품이 존재하여 삭제할 수 없습니다.")
  // ... 다른 예외 코드들
  ;

  final String message;
}
