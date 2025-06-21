package com.sparta.part1.domain.user.entity;

import com.sparta.part1.domain.cart.entity.Cart;
import com.sparta.part1.domain.purchase.entity.Purchase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "password_hash", nullable = false)
    String passwordHash;

    @Column(name = "name", nullable = false, length = 100)
    String name;

    @Column
    String address;

    @Column
    String phoneNumber;

    // 실제 DB에는 없지만 엔티티 상에서만 존재
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Purchase> purchses = new ArrayList<>();

    // Cart엔티티의 user필드 참조
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Cart> carts = new ArrayList<>();


    @Column(nullable = false)
    Boolean isActive = true;

    @Column(nullable = false)
    Integer level = 1;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Builder
    public User(String passwordHash, String name) {
        this.passwordHash = passwordHash;
        this.name = name;
    }
}

