package com.sparta.part_1.common.exception;

public class PurchaseServiceException extends RuntimeException {

  String code;
  String message;


  public PurchaseServiceException(PurchaseErrorCode code) {
    super(code.getMessage());
    this.code = code.name();
    this.message = code.getMessage();
  }

  @Override
  public String getMessage() {
    return message;
  }
}

