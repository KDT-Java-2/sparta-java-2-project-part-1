package com.sparta.java_02.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // User 관련 에러
    USER_NOT_FOUND("사용자를 찾을 수 없습니다."),
    DUPLICATE_EMAIL("이미 존재하는 이메일입니다."),
    INVALID_USER_INPUT("잘못된 사용자 입력입니다."),
    
    // Product 관련 에러
    PRODUCT_NOT_FOUND("상품을 찾을 수 없습니다."),
    DUPLICATE_PRODUCT_NAME("이미 존재하는 상품명입니다."),
    INVALID_PRICE_OR_STOCK("가격이나 재고는 0 이상이어야 합니다."),
    PRODUCT_CANNOT_BE_DELETED("주문 완료된 상품은 삭제할 수 없습니다."),
    
    // Category 관련 에러
    CATEGORY_NOT_FOUND("카테고리를 찾을 수 없습니다."),
    CATEGORY_HAS_CHILDREN("하위 카테고리가 존재하여 삭제할 수 없습니다."),
    CATEGORY_HAS_PRODUCTS("카테고리에 속한 상품이 존재하여 삭제할 수 없습니다."),
    CIRCULAR_REFERENCE("순환 참조는 허용되지 않습니다."),
    
    // 일반적인 에러
    INTERNAL_SERVER_ERROR("서버 내부 오류가 발생했습니다."),
    BAD_REQUEST("잘못된 요청입니다."),
    VALIDATION_ERROR("입력값 검증에 실패했습니다.");

    private final String message;
} 