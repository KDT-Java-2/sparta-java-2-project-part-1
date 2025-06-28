package me.chahyunho.projectweek1.common.enums.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {
  NOT_FOUNT_DATA("데이터를 찾을 수 없습니다."),
  INSUFFICIENT_STOCK("재고가 부족합니다."),
  NOT_FOUNT_USER("사용자를 찾을 수 없습니다."),
  NOT_FOUNT_CATEGORY_PARENT("상위 카테고리가 존재하지 않습니다."),
  NOT_FOUNT_CATEGORY("카테고리가 존재하지 않습니다."),
  DUPLICATE_EMAIL("이미 사용된 이메일입니다."),
  ALREADY_EXISTS_DATA("이미 존재하는 데이터입니다."),
  HAS_ASSOCIATED_PRODUCTS("카테고리에 속한 상품이 없어야 합니다."),
  SUBCATEGORY_EXISTS("하위에 다른 카테고리가 없어야 합니다."),
  SELF_PARENT_NOT_ALLOWED("자기 자신을 부모로 지정할 수 없습니다."),
  INVALID_OPERATION("환불을 진행할 수 없습니다.");
  final String message;
}
