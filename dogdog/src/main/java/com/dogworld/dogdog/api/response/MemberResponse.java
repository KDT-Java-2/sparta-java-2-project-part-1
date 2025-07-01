package com.dogworld.dogdog.api.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponse {

  private Long id;
  private String email;
  private String username;
  private String name;



}
