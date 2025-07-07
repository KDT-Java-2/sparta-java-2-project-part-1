package com.sparta.bootcamp.shop.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.bootcamp.shop.domain.purchase.entity.Purchase;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
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

    @Column(nullable = false)
    String passwordHash;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    LocalDateTime updatedAt;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    List<Purchase> purchases = new ArrayList<>();

    @Builder
    public User(
            String name,
            String email,
            String passwordHash
    ) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public void setName(String name) {
        if (StringUtils.hasText(name)) {
            this.name = name;
        }
    }

    public void setEmail(String email) {
        if (StringUtils.hasText(email)) {
            this.email = email;
        }
    }

    public void setPasswordHash(String passwordHash) {
        if (StringUtils.hasText(passwordHash)) {
            this.passwordHash = passwordHash;
        }
    }

}