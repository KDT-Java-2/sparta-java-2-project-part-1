package com.tommy.ecommerproject.domain.user.entity;

import com.tommy.ecommerproject.common.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

  @Column(nullable = false, updatable = false, unique = true)
  String username;

  @Column(nullable = false)
  String passwordHash;

  @Column(nullable = false, updatable = false, unique = true)
  String email;

  @Column(nullable = false)
  String name;

  @Column(nullable = false)
  String birth;

  @Column(nullable = false)
  String address;

  @Column(nullable = false)
  List<Role> role;

  @Column
  @CreationTimestamp
  LocalDateTime create_at;

  @Column
  @UpdateTimestamp
  LocalDateTime update_at;

  @Builder
  public User(
      String username,
      String passwordHash,
      String email,
      String name,
      String birth,
      String address,
      List<Role> role
  ) {
    this.username = username;
    this.passwordHash = passwordHash;
    this.email = email;
    this.name = name;
    this.birth = birth;
    this.address = address;
    this.role = role;
  }

}
