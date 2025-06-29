package com.scb.project.commerce.domain.user.entity;

import com.scb.project.commerce.common.enums.UserRole;
import com.scb.project.commerce.common.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 50)
    String name;    // 유저 이름

    @Column(nullable = false, unique = true)
    String email;   // 유저 이메일

    @Setter
    @Column(length = 20)
    String phone;   // 유저 연락처

    @Column(nullable = false)
    String passwordHash;    // 유저 비밀번호 해시값

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    UserRole role;  // 유저 권한

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    UserStatus status;  // 사용자 상태

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    LocalDateTime updatedAt;

    @Builder
    public User(
        String name,
        String email,
        String phone,
        String passwordHash,
        UserRole role,
        UserStatus status
    ) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
        this.role = role;
        this.status = status;
    }
}
