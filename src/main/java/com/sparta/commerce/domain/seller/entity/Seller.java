package com.sparta.commerce.domain.seller.entity;

import com.sparta.commerce.domain.product.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Seller {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false, unique = true, length = 255)
  String email;

  @Column(nullable = false, length = 20)
  String name;

  @Column(nullable = false)
  String encryptPassword;

  @Column(length = 20)
  String phoneNumber;

  LocalDateTime lastLoginAt;

  LocalDateTime lastPasswordChangeAt;

  @CreationTimestamp
  LocalDateTime createdAt;

  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public Seller(String email, String name, String encryptPassword, String phoneNumber) {
    this.email = email;
    this.name = name;
    this.encryptPassword = encryptPassword;
    this.phoneNumber = phoneNumber;
  }
}

