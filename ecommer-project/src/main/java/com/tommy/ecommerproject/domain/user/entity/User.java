package com.tommy.ecommerproject.domain.user.entity;

import com.tommy.ecommerproject.common.enums.RoleType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false, unique = true, length = 36)
  String alias;

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
  String phoneNumber;

  @Column(nullable = false)
  String address;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  List<RoleType> role;

  @Column
  @CreationTimestamp
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public User(
      String username,
      String passwordHash,
      String email,
      String name,
      String birth,
      String phoneNumber,
      String address,
      List<RoleType> role
  ) {
    this.username = username;
    this.passwordHash = passwordHash;
    this.email = email;
    this.name = name;
    this.birth = birth;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.role = role;
  }

  // 엔티티가 영속하기 전에 자동으로 호출시키는 어노테이션
  @PrePersist
  public void generatedUuid() {
    if(this.alias == null){ // alias가 존재하지 않으면 UUID를 생성하여 String으로 넣어준다.
      this.alias = UUID.randomUUID().toString();
    }
  }

}
