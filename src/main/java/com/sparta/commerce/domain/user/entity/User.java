package com.sparta.commerce.domain.user.entity;

import com.sparta.commerce.common.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(name = "encrypt_password", nullable = false)
  private String encryptPassword;

  @Column(name = "phone_number")
  private String phoneNumber;

  private String nickname;

  @Column
  private String gender;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  @Column(name = "marketing_agree")
  private Boolean marketingAgree = false;

  @Column(name = "is_verified")
  private Boolean isVerified = false;

  @Column(name = "last_login_at")
  private LocalDateTime lastLoginAt;

  @Column(name = "last_password_change_at")
  private LocalDateTime lastPasswordChangeAt;

  @Column(name = "created_at", updatable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @Builder
  public User(String email, String encryptPassword, String phoneNumber, String nickname,
      String gender, LocalDate birthDate, Role role, Boolean marketingAgree, Boolean isVerified) {
    this.email = email;
    this.encryptPassword = encryptPassword;
    this.phoneNumber = phoneNumber;
    this.nickname = nickname;
    this.gender = gender;
    this.birthDate = birthDate;
    this.role = role;
    this.marketingAgree = marketingAgree;
    this.isVerified = isVerified;
  }
}
