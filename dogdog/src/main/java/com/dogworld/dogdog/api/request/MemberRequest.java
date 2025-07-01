package com.dogworld.dogdog.api.request;

import com.dogworld.dogdog.domain.member.MemberRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class MemberRequest {

  @NotEmpty
  private String username;

  @NotEmpty
  private String password;

  @NotEmpty
  private String name;

  @NotEmpty @Email
  private String email;

  @NotEmpty
  private String phoneNumber;

  private MemberRole role;
  private boolean agreedTerms;
  private boolean agreedPrivacy;
  private boolean agreedMarketing;
  private LocalDateTime marketingAgreedAt;
}
