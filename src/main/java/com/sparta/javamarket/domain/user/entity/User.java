package com.sparta.javamarket.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
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

  @Column(nullable = false, unique = true)
  String email;

  @Column(nullable = false)
  String passwordHash;

  @Column(nullable = false, length = 50)
  String name;

  @Column(nullable = false, length = 50)
  String nickname;

  @Column(nullable = false, length = 50)
  String phone;

  @Column(nullable = false)
  String address;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  public User(String email, String passwordHash, String name, String nickname, String phone,
      String address) {
    this.email = email;
    this.passwordHash = passwordHash;
    this.name = name;
    this.nickname = nickname;
    this.phone = phone;
    this.address = address;
  }
}