package com.dogworld.dogdog.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
  MEMBER("일반 회원"),
  ADMIN("관리자")
  ;

  private final String text;
}
