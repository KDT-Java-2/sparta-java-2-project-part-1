package com.sparta.commerce.domain.user.entity;

import com.sparta.commerce.common.enums.UserStatus;
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
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Table
@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false, length = 50)
  String name;

  @Column(nullable = false, unique = true)
  String email;

  @Column(nullable = false)
  String passwordHash;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  UserStatus status;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public User(String name, String email, String passwordHash, UserStatus status) {
    this.name = name;
    this.email = email;
    this.passwordHash = passwordHash;
    this.status = status;
  }

  public void setStatus(UserStatus status) {
    if (!ObjectUtils.isEmpty(status)) {
      this.status = status;
    }
  }

  public void setEmail(String email) {
    if (!StringUtils.isEmpty(email)) {
      this.email = email;
    }
  }
}
