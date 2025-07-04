package com.sparta.project1.domain.user.entity;

import com.sparta.project1.domain.purchase.entity.Purchase;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.flywaydb.core.internal.util.StringUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 50)
    String name;

    @Column(nullable = false) //기본 길이 255
    String email;

    @Column(nullable = false)
    String password;

    @CreationTimestamp
    @Column(updatable = false, name = "create_at")
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    //1(사용자) : N(다수구매)
    //기본 EAGER(즉시)라서 LAZY 변경
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Purchase> purchases = new ArrayList<>();

    @Builder
    public User(String name, String email) {
        this.name = name;
        this.email = email;
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
}
