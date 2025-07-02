package com.sparta.part_1.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductServiceException extends RuntimeException {

  String code;
  String message;

  public ProductServiceException(ProductErrorCode code) {
    super(code.getMessage());
    this.code = code.name();
    this.message = code.getMessage();
  }

  @Override
  public String getMessage() {
    return message;
  }
}
