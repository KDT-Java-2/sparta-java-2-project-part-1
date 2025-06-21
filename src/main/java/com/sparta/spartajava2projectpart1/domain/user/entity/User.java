package com.sparta.spartajava2projectpart1.domain.user.entity;

import com.sparta.spartajava2projectpart1.domain.cart.entity.Cart;
import com.sparta.spartajava2projectpart1.domain.purchase.entity.Purchase;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 50)
    String name;

    @Column
    String email;

    @Column
    String passwordHash;

    @Column
    Boolean agree;

    @Column
    Boolean thirdAgree;

    @Column
    Boolean marketing;

    @Column
    Boolean deleted;

    @OneToMany(mappedBy = "user")
    List<Purchase> purchases = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Cart> carts = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Builder
    public User(String name, String email, String passwordHash, Boolean agree, Boolean thirdAgree, Boolean marketing) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.agree = agree;
        this.thirdAgree = thirdAgree;
        this.marketing = marketing;
        this.deleted = false;
    }
}
