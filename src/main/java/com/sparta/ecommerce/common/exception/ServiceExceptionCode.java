package com.sparta.ecommerce.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {

  NOT_FOUND_USER("유저를 찾을 수 없습니다."),
  DUPLICATE_USER_EMAIL("사용중인 이메일입니다."),
  NOT_FOUND_PRODUCT("상품을 찾을 수 없습니다."),
  NOT_FOUND_CATEGORY("카테고리를 찾을 수 없습니다."),
  NOT_FOUND_PARENT_CATEGORY("부모 카테고리를 찾을 수 없습니다."),
  DUPLICATE_PROUDCT_NAME("사용중인 상품명입니다."),
  EXISTS_ORDERED_PRODUCT("주문 데이터가 있는 상품은 삭제할 수 없습니다."),
  EXISTS_CHILD_CATEGORY("하위 카테고리가 존재하여 삭제할 수 없습니다."),
  EXISTS_CATEGORY_PRODUCTS("해당 카테고리에 속한 상품이 존재하여 삭제할 수 없습니다."),
  CANNOT_BE_SELF_PARENT("자기 자신을 부모로 지정할 수 없습니다."),
  CIRCULAR_REFERENCE("순환 참조가 발생할 수 있어 부모로 지정할 수 없습니다.");

  final String message;
}
