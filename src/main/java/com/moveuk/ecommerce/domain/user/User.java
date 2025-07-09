package com.moveuk.ecommerce.domain.user;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    @Builder.Default
    private String role = "USER";

    @Column(name = "active_yn", nullable = false, length = 1)
    @Builder.Default
    private String activeYn = "Y";

    @Column(name = "deleted_yn", nullable = false, length = 1)
    @Builder.Default
    private String deletedYn = "N";

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


    public static User create(String email, String rawPassword, PasswordEncryptor encryptor) {
        return User.builder()
                .email(email)
                .passwordHash(encryptor.encrypt(rawPassword)) // 🔒 암호화 여기서 강제
                .build();
    }

    public boolean isPasswordMatch(String rawPassword, PasswordEncryptor encryptor) {
        return encryptor.matches(rawPassword, this.passwordHash);
    }

}
