package com.socialcommerce.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.socialcommerce.common.enums.Gender;
import com.socialcommerce.domain.cart.entity.Cart;
import com.socialcommerce.domain.purchase.entity.Purchase;
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

@Getter
@Entity
@Table
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//@Table(name = "user") // 테이블명(db.migration 안에있는 .sql 안에 테이블명..)과 클래스명이 완전히 같으면 생략이 가능
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false, length = 50)
  String nickName;

  @Column(nullable = false, length = 50)
  String name;
  @Column(nullable = false, unique = true)
  String email;
  @Column(nullable = false)
  @JsonFormat(pattern = "yyyy-MM-dd")
  LocalDate dateOfBirth;
  @Enumerated(EnumType.STRING)
  @Column(length = 10)
  Gender gender; // 'MALE', 'FEMALE', 'NONE'
  @Column(nullable = false, length = 255)
  String phoneNumberHash;

  // 카멜표기시 생략가능
  //@Column(name = "password_hash")
  @Column(nullable = false, length = 255)
  String passwordHash;

  // 1:N
  @OneToMany(mappedBy = "user")
  @JsonManagedReference
  List<Cart> carts;

  // User 가 여러개의 Purchase 를 가지고 있다 = 1:N 관계
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  @JsonManagedReference
  List<Purchase> purchases = new ArrayList<>();

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;
  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

@Builder
  public User(
      String name,
      String nickName,
      String email,
      LocalDate dateOfBirth,
      Gender gender,
      String phoneNumberHash,
      String passwordHash
  ) {
    this.name = name;
    this.nickName = nickName;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.phoneNumberHash = phoneNumberHash;
    this.passwordHash = passwordHash;

  }
}
