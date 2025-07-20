package com.sparta.common.enums;

public enum UserStaus {
  ACTIVE("활성"), INACTIVE("비활성"), BANNED("사용불가");

  private final String description;

  UserStaus(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
