package com.sparta.commerce_project_01.domain.user.entity;

import com.sparta.commerce_project_01.domain.purchase.entity.Purchase;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor // 빈생성자를 만들어 준다  vs  AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE) // 기본 접근 제한자 지정
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 50)
    String name;

    @Column(nullable = false, length = 50)
    String email;

    @Column(length = 20)
    String cellPhone;

    @Column(nullable = false)
    @Setter
    String passwordHash;

    @Column
    Boolean isActive;

    @Column
    @Setter
    LocalDateTime lastLogin;

    @Column
    @Setter
    LocalDateTime acceptTermsAt;

    @Column
    @Setter
    LocalDateTime acceptPrivacyAt;

    @Column
    @Setter
    LocalDateTime acceptMarketingAt;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Purchase> purchases;

    @Builder // 생성자의 순서와 상관없이 객체를 생성할 수 있다
    public User(
            String name,
            String email,
            String passwordHash
    ) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
    }
}
