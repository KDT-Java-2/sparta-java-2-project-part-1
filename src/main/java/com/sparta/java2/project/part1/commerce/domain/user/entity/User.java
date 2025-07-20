package com.sparta.java2.project.part1.commerce.domain.user.entity;

import com.sparta.java2.project.part1.commerce.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@Table
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 50)
    String name;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String passwordHash;

    @Column(nullable = false, updatable = false)
    char gender;

    @Column(updatable = false)
    LocalDateTime birthDay;

    @Column(nullable = false, length = 11)
    String phoneNumber;

    @Column(length = 5)
    String postalCode;

    @Column
    String address;

    @Column
    String profileImageUrl;

    @Column
    char personalInfoConsentYn;

    @Builder
    public User(String name, String email, String passwordHash, char gender, LocalDateTime birthDay, String phoneNumber, String postalCode, String address, String profileImageUrl, char personalInfoConsentYn) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.gender = gender;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
        this.address = address;
        this.profileImageUrl = profileImageUrl;
        this.personalInfoConsentYn = personalInfoConsentYn;
    }
}
