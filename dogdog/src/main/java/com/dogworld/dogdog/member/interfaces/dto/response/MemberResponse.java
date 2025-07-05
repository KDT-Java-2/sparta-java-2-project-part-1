package com.dogworld.dogdog.member.interfaces.dto.response;

import com.dogworld.dogdog.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponse {

  private Long id;
  private String email;
  private String username;
  private String name;


  public static MemberResponse from(Member member) {
    return MemberResponse.builder()
        .id(member.getId())
        .username(member.getUsername())
        .email(member.getEmail())
        .name(member.getName())
        .build();
  }
}
