package com.example.shoppingmall.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {
    // 상품/재고
    NOT_FOUND_PRODUCT("상품을 찾을 수 없습니다."),
    INSUFFICIENT_STOCK("상품의 재고가 부족합니다."),
    PRODUCT_ALREADY_DELETED("이미 삭제된 상품입니다."),
    INVALID_PRODUCT_STATUS("유효하지 않은 상품 상태입니다."),
    DUPLICATE_PRODUCT_NAME("이미 존재하는 상품명입니다."),

    // 주문
    NOT_FOUND_ORDER("주문을 찾을 수 없습니다."),
    INVALID_ORDER_STATUS("유효하지 않은 주문 상태입니다."),
    ORDER_ALREADY_CANCELLED("이미 취소된 주문입니다."),
    ORDER_ALREADY_COMPLETED("이미 완료된 주문입니다."),

    // 결제
    PAYMENT_FAILED("결제에 실패했습니다."),
    INVALID_PAYMENT_METHOD("유효하지 않은 결제 수단입니다."),
    PAYMENT_ALREADY_COMPLETED("이미 결제가 완료된 주문입니다."),
    PAYMENT_NOT_FOUND("결제 정보를 찾을 수 없습니다."),

    // 장바구니/쿠폰
    CART_EMPTY("장바구니가 비어 있습니다."),
    COUPON_NOT_FOUND("쿠폰을 찾을 수 없습니다."),
    COUPON_ALREADY_USED("이미 사용된 쿠폰입니다."),
    COUPON_EXPIRED("쿠폰이 만료되었습니다."),

    // 사용자/인증
    USER_NOT_FOUND("사용자를 찾을 수 없습니다."),
    DUPLICATE_EMAIL("이미 사용 중인 이메일입니다."),
    INVALID_CREDENTIALS("아이디 또는 비밀번호가 올바르지 않습니다."),
    UNAUTHORIZED("인증이 필요합니다."),
    FORBIDDEN("권한이 없습니다."),

    // 카테고리
    CATEGORY_NOT_FOUND("카테고리를 찾을 수 없습니다."),
    UNCATEGORIZED_CATEGORY_NOT_FOUND("미분류 카테고리가 없습니다."),
    CATEGORY_HAS_CHILDREN("하위 카테고리가 있어 삭제할 수 없습니다."),
    CATEGORY_HAS_PRODUCTS("카테고리에 상품이 있어 삭제할 수 없습니다."),
    CATEGORY_CIRCULAR_REFERENCE("순환 참조는 허용되지 않습니다."),

    // 상품
    PRODUCT_NOT_FOUND("상품을 찾을 수 없습니다."),
    INVALID_OPTION_VALUE("유효하지 않은 옵션 값입니다."),
    PRODUCT_CANNOT_DELETE_HAS_COMPLETED_ORDERS("완료된 주문에 포함된 상품은 삭제할 수 없습니다."),

    // 장바구니
    CART_NOT_FOUND("장바구니를 찾을 수 없습니다."),
    CART_ITEM_NOT_FOUND("장바구니 상품을 찾을 수 없습니다."),

    // 기타
    INVALID_REQUEST("잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR("서버 내부 오류가 발생했습니다.");

    final String message;
} 