package com.sparta.part_1.domain.user.entity;

import com.sparta.part_1.common.enums.UserGrade;
import com.sparta.part_1.common.enums.UserStatus;
import com.sparta.part_1.domain.cart.entity.Cart;
import com.sparta.part_1.domain.purchase.entity.Purchase;
import com.sparta.part_1.domain.refunds.entity.Refund;
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

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false, length = 50)
  String name;

  @Column(nullable = false)
  String email;

  @Column(name = "password_hash", nullable = false)
  String password;

  @Column(nullable = false, length = 10)
  @Enumerated(EnumType.STRING)
  UserStatus status;

  @Column(nullable = false)
  LocalDateTime birth;

  @Column(nullable = false, length = 15)
  String phoneNumber;

  @Column(nullable = false, length = 10)
  @Enumerated(EnumType.STRING)
  UserGrade grade;

  @Column(nullable = false)
  Boolean isPersonalInfoAgree;

  @Column(nullable = false)
  Boolean isThirdPartyAgree;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  List<Cart> cart = new ArrayList<>();

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  List<Refund> refunds = new ArrayList<>();

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  List<Purchase> purchases = new ArrayList<>();

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public User
      (
          String name,
          String email,
          String password,
          UserStatus status,
          LocalDateTime birth,
          String phoneNumber,
          UserGrade grade,
          Boolean isPersonalInfoAgree,
          Boolean isThirdPartyAgree
      ) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.status = status;
    this.birth = birth;
    this.phoneNumber = phoneNumber;
    this.grade = grade;
    this.isPersonalInfoAgree = isPersonalInfoAgree;
    this.isThirdPartyAgree = isThirdPartyAgree;
  }
}
