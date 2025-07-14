package com.socialcommerce.domain.coupon;

import com.socialcommerce.domain.purchase.entity.Purchase;
import com.socialcommerce.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Entity
@Table(name = "coupon_usage")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor  // 빈생성자를 만들어준다. 항상 있는데 귀찮기 때문에
@FieldDefaults(level = AccessLevel.PRIVATE) // 모든 접근 제한이 private 으로 바뀐다.
public class CouponUsage {
    @Id @GeneratedValue
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    Coupon coupon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    Purchase purchase;

    @Column
    LocalDateTime usedAt; // 쿠폰 사용 시각

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    LocalDateTime updatedAt;

    @Builder
    public CouponUsage(Coupon coupon, User user, Purchase purchase, LocalDateTime usedAt) {
        this.coupon = coupon;
        this.user = user;
        this.purchase = purchase;
        this.usedAt = usedAt;
    }
}
