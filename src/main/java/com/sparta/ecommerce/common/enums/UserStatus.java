package com.sparta.ecommerce.common.enums;

public enum UserStatus {
  NORMAL("정상"), LOCK("대기"), DELETED("삭제됨");

  private final String description;

  UserStatus(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
