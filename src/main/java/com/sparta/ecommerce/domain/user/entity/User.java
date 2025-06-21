package com.sparta.ecommerce.domain.user.entity;

import com.sparta.ecommerce.common.enums.UserStatus;
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

  @Column(nullable = false, length = 50)
  String userNm;

  @Column
  String email;

  @Column(nullable = false)
  String passwordHash;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 10)
  UserStatus userStatus;

  @Column
  LocalDateTime lastLoginAt;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public User(String userNm, String email, String passwordHash, UserStatus userStatus,
      LocalDateTime lastLoginAt) {
    this.userNm = userNm;
    this.email = email;
    this.passwordHash = passwordHash;
    this.userStatus = userStatus;
    this.lastLoginAt = lastLoginAt;
  }
}

