package com.example.shoppingmall.domain.purchase.entity;

import com.example.shoppingmall.common.enums.PurchaseStatus;
import com.example.shoppingmall.domain.refund.entity.Refund;
import com.example.shoppingmall.domain.user.entity.User;
import com.example.shoppingmall.domain.user.entity.UserCoupon;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "purchase")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "used_user_coupon_id")
    private UserCoupon usedUserCoupon;

    @Column(precision = 19, scale = 2)
    private BigDecimal couponDiscountAmount = BigDecimal.ZERO;

    @Column(precision = 19, scale = 2)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PurchaseStatus status;

    @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PurchaseProduct> purchaseProducts = new ArrayList<>();

    @OneToOne(mappedBy = "purchase", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Refund refund;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt;

    @Builder
    public Purchase(User user, UserCoupon userCoupon, BigDecimal totalPrice, PurchaseStatus status, BigDecimal couponDiscountAmount) {
        this.user = user;
        this.usedUserCoupon = userCoupon;
        this.totalPrice = totalPrice;
        this.status = status;
        this.couponDiscountAmount = couponDiscountAmount;
    }

    public void updateStatus(PurchaseStatus status) {
        this.status = status;
    }
} 