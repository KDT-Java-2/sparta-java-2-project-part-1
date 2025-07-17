package com.sparta.java2_project_part1.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
  String name;

  @Column
  String email;

  // 카멜형식은 자동으로 스네이크로 인식해서 이것도 name 생략가능
  @Column
  String passwordHash;

  @Column
  String gender;

  @Column
  String phonNumber;

  @Column
  String address;

  @Column
  String userAgreement;

  @Column
  String consentPrivacyPolicy;

  // CreationTimestamp, UpdateTimestamp 특수한 칼럼이라 어노테이션이 따로 있음.
  @CreationTimestamp
  @Column(nullable = false, updatable = false) //업데이트가 되면 안되니깐!
      LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder // builder.~~.build();로 설정해서 순서나 하고싶은 칼럼만 넣을 수 있는 장점이 있다.
  public User(
      String name,
      String email,
      String passwordHash,
      String gender,
      String phonNumber,
      String address,
      String userAgreement,
      String consentPrivacyPolicy
  ) {
    this.name = name;
    this.email = email;
    this.passwordHash = passwordHash;
    this.gender = gender;
    this.phonNumber = phonNumber;
    this.address = address;
    this.userAgreement = userAgreement;
    this.consentPrivacyPolicy = consentPrivacyPolicy;
  }
}
