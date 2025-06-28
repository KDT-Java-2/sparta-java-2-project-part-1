package com.example.shoppingmall.domain.user.entity;

import com.example.shoppingmall.common.enums.Gender;
import com.example.shoppingmall.common.enums.LoginType;
import com.example.shoppingmall.common.enums.UserRole;
import com.example.shoppingmall.common.enums.UserStatus;
import com.example.shoppingmall.domain.cart.entity.Cart;
import com.example.shoppingmall.domain.purchase.entity.Purchase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name = "user")
@Entity
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(columnDefinition = "BINARY(16)", nullable = false, updatable = false, unique = true)
    UUID uuid;

    @Column(length = 20, unique = true)
    String customerNumber;

    @Column(nullable = false, length = 20)
    String name;

    @Column(nullable = false, unique = true, length = 50)
    String email;

    @Column(nullable = false, length = 255)
    String passwordHash;

    @Column(nullable = false, length = 20)
    String phone;

    @Enumerated(EnumType.STRING)
    Gender gender;

    LocalDate birthDate;

    @Column(length = 500)
    String profileImageUrl;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'EMAIL'")
    LoginType loginType = LoginType.EMAIL;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ACTIVE'")
    UserStatus status = UserStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'USER'")
    UserRole role = UserRole.USER;

    LocalDateTime lastLoginAt;

    @Column(nullable = false)
    LocalDateTime termsAgreedAt;

    @Column(nullable = false)
    LocalDateTime privacyAgreedAt;

    LocalDateTime marketingAgreedAt;

    @ColumnDefault("false")
    Boolean isEmailVerified = false;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    // 연관관계 매핑
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Purchase> purchases = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<UserCoupon> userCoupons = new ArrayList<>();

    @Builder
    public User(String name, String email, String passwordHash, String phone, Gender gender,
                LocalDate birthDate, String profileImageUrl, LoginType loginType, UserStatus status,
                UserRole role, LocalDateTime termsAgreedAt, LocalDateTime privacyAgreedAt, LocalDateTime marketingAgreedAt) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phone = phone;
        this.gender = gender;
        this.birthDate = birthDate;
        this.profileImageUrl = profileImageUrl;
        this.loginType = loginType != null ? loginType : LoginType.EMAIL;
        this.status = status != null ? status : UserStatus.ACTIVE;
        this.role = role != null ? role : UserRole.USER;
        this.termsAgreedAt = termsAgreedAt;
        this.privacyAgreedAt = privacyAgreedAt;
        this.marketingAgreedAt = marketingAgreedAt;
    }

    // 역할 확인 메서드들
    public boolean isUser() {
        return this.role == UserRole.USER;
    }

    public boolean isProductAdmin() {
        return this.role == UserRole.PRODUCT_ADMIN;
    }

    public boolean isSuperAdmin() {
        return this.role == UserRole.SUPER_ADMIN;
    }

    public boolean hasAdminRole() {
        return this.role == UserRole.PRODUCT_ADMIN || this.role == UserRole.SUPER_ADMIN;
    }

    @PrePersist
    public void assignUuid() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
    }
} 