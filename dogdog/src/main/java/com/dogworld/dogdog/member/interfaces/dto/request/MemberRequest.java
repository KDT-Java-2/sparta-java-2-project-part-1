package com.dogworld.dogdog.member.interfaces.dto.request;

import com.dogworld.dogdog.member.domain.MemberRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberRequest {

  @NotBlank(message = "{validation.member.username.required}")
  private String username;

  @NotBlank(message = "{validation.member.password.required}")
  private String password;

  @NotBlank(message = "{validation.member.name.required}")
  private String name;

  @NotBlank(message = "{validation.member.email.required}")
  @Email(message = "{validation.member.email.invalid}")
  private String email;

  @NotBlank(message = "{validation.member.phone.required}")
  private String phoneNumber;

  private MemberRole role;
  private boolean agreedTerms;
  private boolean agreedPrivacy;
  private boolean agreedMarketing;
}
