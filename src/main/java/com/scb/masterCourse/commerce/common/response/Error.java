package com.scb.masterCourse.commerce.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public record Error(String errorCode, String errorMessage) {

    public static Error of(String errorCode, String errorMessage) {
        return new Error(errorCode, errorMessage);
    }
}
