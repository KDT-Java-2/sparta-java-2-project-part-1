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
    ;


    final String message;
}
