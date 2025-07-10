package com.example.chaenchaen.domain.user.entity;

import com.example.chaenchaen.domain.purchase.entity.Purchase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
import org.springframework.util.StringUtils;

@Entity
@Table(name = "user")
@Getter
@DynamicUpdate //변경된 필드만 update 쿼리를 작성해줌
@DynamicInsert //null인 친구들은 제외하고 insert query 작성해줌
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false)
  String name;

  @Column(nullable = false)
  String email;

  @Column(name = "password_hash", nullable = false)
  String passwordHash;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(nullable = false)
  LocalDateTime updatedAt;

  @OneToMany(mappedBy = "user")
  @JoinColumn
  List<Purchase> purchases = new ArrayList<>();


  @Builder
  public User(String name, String email, String passwordHash) {
    this.name = name;
    this.email = email;
    this.passwordHash = passwordHash;
  }

  private void setName(String name) {
    if (StringUtils.hasText(name)) {
      this.name = name;
    }
  }

  private void setEmail(String email) {
    if (StringUtils.hasText(email)) {
      this.email = email;
    }
  }

  private void setPasswordHash(String passwordHash) {
    if (StringUtils.hasText(passwordHash)) {
      this.passwordHash = passwordHash;
    }
  }
}
