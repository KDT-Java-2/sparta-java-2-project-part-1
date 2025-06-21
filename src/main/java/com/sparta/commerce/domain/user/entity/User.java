package com.sparta.commerce.domain.user.entity;

import com.sparta.commerce.common.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Builder
  public User(
      String name,
      String email,
      String passwordHash,
      String phoneNumber,
      Boolean isDeleted,
      UserRole role) {
    this.name = name;
    this.email = email;
    this.passwordHash = passwordHash;
    this.phoneNumber = phoneNumber;
    // 기본값 설정 (null이 들어올 경우)
    this.isDeleted = (isDeleted != null) ? isDeleted : false;
    this.role = (role != null) ? role : UserRole.USER;
  }

  @Column(nullable = false, length = 50)
  String name; // 유저 이름

  @Column(nullable = false, unique = true)
  String email; // 유저 이메일

  @Column(nullable = false)
  String passwordHash; // 유저 비밀번호

  @Column(nullable = true, length = 20)
  String phoneNumber; // 유저 전화번호

  @Column(nullable = false)
  boolean isDeleted = false; // 탈퇴 여부, 기본값 false

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  UserRole role = UserRole.USER; // 사용자 역할

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;

  @Column(nullable = false)
  @UpdateTimestamp
  LocalDateTime updatedAt;

  /**
   * 유저 탈퇴 처리 메서드
   * isDeleted 필드를 true로 설정하여 탈퇴 상태로 변경합니다.
   */
  public void withDrawUser() {
    this.isDeleted = true;
  }

}
