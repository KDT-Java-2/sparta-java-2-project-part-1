package com.sparta.commerce_project_01.domain.user.entity;

import com.sparta.commerce_project_01.common.enums.UserRole;
import com.sparta.commerce_project_01.common.enums.UserStatus;
import com.sparta.commerce_project_01.domain.purchase.entity.Purchase;
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
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@NoArgsConstructor // 빈생성자를 만들어 준다  vs  AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE) // 기본 접근 제한자 지정
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false, length = 50)
  String name;

  @Column(nullable = false, length = 50)
  String email;

  @Column(length = 20)
  String cellPhone;

  @Column(nullable = false)
  @Setter
  String passwordHash;

  @Column
  Boolean isActive;

  @Column
  @Setter
  LocalDateTime lastLogin;

  @Column
  @Setter
  LocalDateTime acceptTermsAt;

  @Column
  @Setter
  LocalDateTime acceptPrivacyAt;

  @Column
  @Setter
  LocalDateTime acceptMarketingAt;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  UserRole role;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  UserStatus status;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;

  @Column(nullable = false)
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  private List<Purchase> purchases;

  @Builder // 생성자의 순서와 상관없이 객체를 생성할 수 있다
  public User(
      String name,
      String email,
      String passwordHash,
      UserRole role
  ) {
    this.name = name;
    this.email = email;
    this.passwordHash = passwordHash;
    this.role = role;
  }
}
