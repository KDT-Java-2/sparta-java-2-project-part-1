package com.sparta.part_1.domain.user.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserJoinRequest {

  @Min(value = 1, message = "이름은 1글자 일 수 없습니다.")
  String name;

  @Email
  @NotNull
  String email;

  @Size(min = 8, message = "비밀번호는 8자리 이상이어야합니다.")
  String passwordHash;

  @NotNull(message = "개인정보 제공동의가 전달되지 않았습니다.")
  Boolean isPersonalInfoAgree;

  @NotNull(message = "제 3자 제공동의 값이 전달되지 않았습니다.")
  Boolean isThirdPartyAgree;

  @NotNull(message = "생일을 입력하지 않았습니다!")
  @Past(message = "생일은 현재날짜보다 앞설 수 없습니다.")
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  LocalDateTime birth;

  @NotNull(message = "전화번호 값이 입력되지 않았습니다!")
  String phoneNumber;


}
