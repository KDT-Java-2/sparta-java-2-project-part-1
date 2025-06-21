package com.java_project.part_1.domain.user.entity;

import com.java_project.part_1.common.enums.Role;
import com.java_project.part_1.domain.purchase.entity.Purchase;
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
  String userId;

  @Column(nullable = false)
  String password;

  @Column(nullable = false, length = 50)
  String name;

  @Column
  String email;

  @Column(length = 50)
  String phoneNumber;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  Role role;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column()
  LocalDateTime updatedAt;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  List<Purchase> purchases = new ArrayList<>();

  @Builder
  public User(
      String userId,
      String password,
      String name,
      Role role) {
    this.userId = userId;
    this.password = password;
    this.name = name;
    this.role = role;
  }
}

