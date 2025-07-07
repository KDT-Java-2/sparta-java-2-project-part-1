package com.sparta.part_1.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum CategoryErrorCode {
  HAS_NOT_FOUND_CATEGORY("카테고리가 0건입니다!"),
  NOT_FOUND_PARENT("부모 카테고리를 찾을 수 없습니다!"),
  HAS_CHILD_CATEGORY("자식 카테고리가 존재합니다!"),
  HAS_PRODUCT_IN_CATEGORY("해당 카테고리를 가진 상품이 존재합니다!\n 해당 상품의 삭제를 먼저 진행해주세요.");


  final String message;
}
