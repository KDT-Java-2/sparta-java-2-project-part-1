package com.moveuk.ecommerce.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_agreements")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserAgreement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder.Default
    @Column(name = "terms_agreed_yn", nullable = false, length = 1)
    private String termsAgreedYn = "N";

    @Column(name = "terms_agreed_at")
    private LocalDateTime termsAgreedAt;

    @Builder.Default
    @Column(name = "privacy_agreed_yn", nullable = false, length = 1)
    private String privacyAgreedYn = "N";

    @Column(name = "privacy_agreed_at")
    private LocalDateTime privacyAgreedAt;

    @Builder.Default
    @Column(name = "marketing_agreed_yn", nullable = false, length = 1)
    private String marketingAgreedYn ="N";

    @Column(name = "marketing_agreed_at")
    private LocalDateTime marketingAgreedAt;

    @Column(name = "created_at", updatable = false)
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
}
