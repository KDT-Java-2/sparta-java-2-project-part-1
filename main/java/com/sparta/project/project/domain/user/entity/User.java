package com.sparta.project.project.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.project.project.common.enums.UserGender;
import com.sparta.project.project.domain.cart.entity.Cart;
import com.sparta.project.project.domain.purchase.entity.Purchase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name = "user")
@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

  @Column(nullable = false, unique = true, length = 20)
  String nickName;

  @Column(unique = true, length = 20)
  String phone;

  @Column(columnDefinition = "TEXT", nullable = false)
  String address;

  @Column(nullable = false)
  Integer age;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 1)
  UserGender gender;

  @Column(length = 100, nullable = false)
  String refundAccountHolder;

  @Column(length = 100, nullable = false)
  String bankName;

  @Column(length = 50, nullable = false)
  String refundAccountNumber;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  List<Purchase> purchase = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  List<Cart> cart = new ArrayList<>();

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(nullable = false)
  LocalDateTime updatedAt;

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public void setGender(UserGender gender) {
    this.gender = gender;
  }

  public void setRefundAccountHolder(String refundAccountHolder) {
    this.refundAccountHolder = refundAccountHolder;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public void setRefundAccountNumber(String refundAccountNumber) {
    this.refundAccountNumber = refundAccountNumber;
  }

}
