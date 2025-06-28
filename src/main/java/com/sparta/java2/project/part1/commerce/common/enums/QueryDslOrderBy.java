package com.sparta.java2.project.part1.commerce.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QueryDslOrderBy {
    ASC("asc"),
    DESC("desc");

    private final String value;
}
