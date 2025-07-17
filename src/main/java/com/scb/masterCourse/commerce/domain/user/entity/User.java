package com.scb.masterCourse.commerce.domain.user.entity;

import com.scb.masterCourse.commerce.common.enums.UserRole;
import com.scb.masterCourse.commerce.common.enums.UserStatus;
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
    String name;

    @Column(nullable = false, unique = true, length = 50)
    String nickname;

    @Column(nullable = false, unique = true)
    String email;

    @Column(length = 20)
    String phoneNumber;

    @Column(nullable = false)
    String password;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    UserStatus status;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    UserRole role;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    LocalDateTime updatedAt;

    @Builder
    public User(
        String name,
        String nickname,
        String email,
        String phoneNumber,
        String password,
        UserStatus status,
        UserRole role
    ) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.status = status;
        this.role = role;
    }
}
