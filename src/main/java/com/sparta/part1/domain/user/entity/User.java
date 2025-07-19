package com.sparta.part1.domain.user.entity;

import com.sparta.part1.domain.cart.entity.Cart;
import com.sparta.part1.domain.purchase.purchase.Purchase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.StringUtils;


@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
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
  String phone;

  @Column(nullable = false)
  String passwordHash;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(nullable = false)
  LocalDateTime updatedAt;

  @Builder
  public User(String name, String email, String phone, String passwordHash) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.passwordHash = passwordHash;
  }

  public void setName(String name) {
    if (StringUtils.hasText(name)) {
      this.name = name;
    }
  }

  public void setEmail(String email) {
    if (StringUtils.hasText(email)) {
      this.email = email;
    }
  }

  public void setPhone(String phone) {
    if (StringUtils.hasText(phone)) {
      this.phone = phone;
    }
  }

  public void setPasswordHash(String passwordHash) {
    if (StringUtils.hasText(passwordHash)) {
      this.passwordHash = passwordHash;
    }
  }

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  List<Purchase> purchases;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  List<Cart> carts;
}