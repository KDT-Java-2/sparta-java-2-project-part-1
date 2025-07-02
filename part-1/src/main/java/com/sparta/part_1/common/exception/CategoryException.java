package com.sparta.part_1.common.exception;

public class CategoryException extends RuntimeException {

    String code;
    String message;


    public CategoryException(CategoryErrorCode code) {
        super(code.getMessage());
        this.code = code.name();
        this.message = code.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
