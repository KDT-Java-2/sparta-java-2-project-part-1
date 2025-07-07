package com.sparta.commerce.common.enums;

import lombok.Getter;

@Getter
public enum Gender {
  MALE("남성"),
  FEMALE("여성"),
  OTHER("기타");

  private final String description;

  Gender(String description) {
    this.description = description;
  }
}
