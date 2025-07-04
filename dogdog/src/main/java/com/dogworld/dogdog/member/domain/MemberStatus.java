package com.dogworld.dogdog.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberStatus {
  ACTIVE("정상 회원"),
  INACTIVE("휴면 회원"),
  WITHDRAWN("탈퇴 회원"),
  SUSPENDED("정지 회원")
  ;

  private final String text;
}
