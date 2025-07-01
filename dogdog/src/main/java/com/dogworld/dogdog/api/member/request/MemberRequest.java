package com.dogworld.dogdog.api.member.request;

import com.dogworld.dogdog.domain.member.MemberRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberRequest {

  @NotBlank
  private String username;

  @NotBlank
  private String password;

  @NotBlank
  private String name;

  @NotBlank @Email
  private String email;

  @NotBlank
  private String phoneNumber;

  private MemberRole role;
  private boolean agreedTerms;
  private boolean agreedPrivacy;
  private boolean agreedMarketing;
}
