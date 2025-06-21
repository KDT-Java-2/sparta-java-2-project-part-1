package com.sparta.shopping.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@Table(name="users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(name="password_hash",nullable = false)
  String passwordHash;

  @Column(nullable = false)
  String name;

  @Column(nullable = false, unique = true)
  String email;

  @Column(name = "phone_number",nullable = false)
  String phoneNumber;

  @Column(name="del_yn",nullable = false, length = 1)
  String delYn;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column
  LocalDateTime updatedAt;

  @Column
  LocalDateTime deletedAt;

  @Builder
  public User(String passwordHash, String name, String email, String phoneNumber) {
    this.passwordHash = passwordHash;
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.delYn = "N";
  }
}
