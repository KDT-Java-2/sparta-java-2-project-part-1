package com.sparta.coupang_commerce.domain.user.entity;

import com.sparta.coupang_commerce.common.enums.GenderType;
import com.sparta.coupang_commerce.infra.persistence.convertor.CryptoConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;
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
  String name;

  @Column(nullable = false, unique = true)
  String email;

  @Column(nullable = false)
  String passwordHash;

  @Column(nullable = false, unique = true)
  private String phoneNumHash;          // SHA-256 같은 해시값, 인덱스 걸어서 검색용

  @Convert(converter = CryptoConverter.class)
  @Column(nullable = false)
  private String phoneNum;     // 실제 암호화된 값

  @Column(nullable = false)
  Date dateOfBirth;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  GenderType gender;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder //Entity에 한에서는 클래스에 붙이지 않는다.
  public User(String name, String email, String passwordHash, String phoneNumHash, String phoneNum, Date dateOfBirth, GenderType gender) {
    this.name = name;
    this.email = email;
    this.passwordHash = passwordHash;
    this.phoneNumHash = phoneNumHash;
    this.phoneNum = phoneNum;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
  }
}
