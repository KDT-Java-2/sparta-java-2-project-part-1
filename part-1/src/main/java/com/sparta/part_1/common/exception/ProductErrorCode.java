package com.sparta.part_1.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * 유저 도메인 관련 에러
 */
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ProductErrorCode {

    NOT_FOUND_PRODUCT_FOR_ID("상품 ID로 조회된 건이 0건입니다."),
    ;

    final String message;

}
