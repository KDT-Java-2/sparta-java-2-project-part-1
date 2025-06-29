package com.scb.project.commerce.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {

    NOT_FOUND_DATA("데이터를 찾을 수 없습니다."),
    NOT_FOUND_PRODUCT("상품을 찾을 수 없습니다."),
    NOT_FOUND_CATEGORY("카테고리를 찾을 수 없습니다."),

    DUPLICATE_EMAIL("이미 사용 중인 이메일입니다."),
    DUPLICATE_PRODUCT_NAME("이미 사용 중인 상품명입니다."),

    INVALID_NEGATIVE_VALUE("음수는 허용되지 않는 값입니다."),
    INVALID_PARENT_SELF_REFERENCE("부모는 자기 자신이 될 수 없습니다."),

    CANNOT_DELETE_COMPLETED_PRODUCT("배송중이거나 배송완료된 상품은 삭제할 수 없습니다."),
    CANNOT_DELETE_HAS_CHILD("하위 카테고리가 존재하여 삭제할 수 없습니다."),
    CANNOT_DELETE_HAS_PRODUCT("해당 카테고리에는 상품이 존재하여 삭제할 수 없습니다."),
    ;

    final String message;
}
