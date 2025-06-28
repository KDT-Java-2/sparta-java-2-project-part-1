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
public enum UserErrorCode {

  USER_NOT_FOUND("유저를 찾을 수 없습니다."),
  USER_SAVE_FAILED("회원 가입에 실패했습니다.");

  final String message;

}
