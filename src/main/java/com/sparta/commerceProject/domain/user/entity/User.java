package com.sparta.commerceProject.domain.user.entity;

import com.sparta.commerceProject.domain.cart.entity.Cart;
import com.sparta.commerceProject.domain.purchase.entity.Purchase;
import com.sparta.commerceProject.domain.refund.entity.Refund;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table
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

  @Column(nullable = false)
  String password;

  @Column(nullable = false, length = 50)
  String birth;

  @Column(nullable = false)
  String address;

  @Column(nullable = false, length = 50)
  String phone;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(nullable = false)
  LocalDateTime updatedAt;

  @OneToMany(mappedBy = "user")
  List<Purchase> purchases = new ArrayList<>();

  @OneToMany(mappedBy = "user")
  List<Cart> cart = new ArrayList<>();

  @OneToMany(mappedBy = "user")
  List<Refund> refunds = new ArrayList<>();

  @Builder
  public User(Long id, String name, String email, String password, String birth, String address,
      String phone) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.birth = birth;
    this.address = address;
    this.phone = phone;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setBirth(String birth) {
    this.birth = birth;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
