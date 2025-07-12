package com.sparta.javamarket.domain.user.entity;

import com.sparta.javamarket.common.enums.UserRoleStatus;
import com.sparta.javamarket.domain.purchase.entity.Purchase;
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
import org.springframework.util.ObjectUtils;

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

  @Column(nullable = false, unique = true)
  String email;

  @Column(nullable = false)
  String password;

  @Column(nullable = false, length = 50)
  String name;

  @Column(nullable = false, length = 50)
  String nickname;

  @Column(nullable = false, length = 50)
  String phone;

  @Column(nullable = false)
  String address;

  @Enumerated(EnumType.STRING)
  @Column
  UserRoleStatus role;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public User(String email, String password, String name, String nickname, String phone,
      String address, UserRoleStatus role) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.nickname = nickname;
    this.phone = phone;
    this.address = address;
    this.role = role;
  }

  public void setName(String name) {
   if(!ObjectUtils.isEmpty(name)) {
     this.name = name;
   }
  }

  public void setNickname(String nickname) {
    if(!ObjectUtils.isEmpty(nickname)){
      this.nickname = nickname;
    }
  }

  public void setPassword(String password) {
    if(!ObjectUtils.isEmpty((password))){
      this.password = password;
    }
  }

  public void setPhone(String phone) {
    if(!ObjectUtils.isEmpty(phone)){
      this.phone = phone;
    }
  }

  public void setRole(UserRoleStatus role) {
    if(!ObjectUtils.isEmpty((role))){
      this.role = role;
    }
  }
}