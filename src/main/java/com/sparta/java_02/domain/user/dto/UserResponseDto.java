package com.sparta.java_02.domain.user.dto;

import com.sparta.java_02.domain.user.entity.User;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {

  private final Long id;
  private final String name;
  private final String email;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  // User 엔티티를 받아서 DTO를 생성하는 생성자
  public UserResponseDto(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.email = user.getEmail();
    this.createdAt = user.getCreatedAt();
    this.updatedAt = user.getUpdatedAt();
  }

}
