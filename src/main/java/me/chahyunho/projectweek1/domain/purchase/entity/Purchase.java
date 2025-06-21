package me.chahyunho.projectweek1.domain.purchase.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.chahyunho.projectweek1.common.enums.PurchaseStatus;
import me.chahyunho.projectweek1.domain.user.entity.User;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Purchase { // 주문

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  //@ManyToOne(fetch = FetchType.EAGER) // lazy loading을 쓰지않는경우 명시적으로 적어주는걸 권장한다.
  @JoinColumn(name = "user_id", nullable = false)
  User user;

  @Column
  BigDecimal totalPrice;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  PurchaseStatus status;

  @CreationTimestamp // 엔티티 생성시 시간이 자동으로 기록됨
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp // 엔티티 수정시 시간이 자동으로 기록됨
  @Column(nullable = false, updatable = false)
  LocalDateTime updatedAt;

  public void setStatus(PurchaseStatus status) {
    if (!ObjectUtils.isEmpty(status)) {
      this.status = status;
    }

  }

  @Builder
  public Purchase(
      User user,
      BigDecimal totalPrice,
      PurchaseStatus status
  ) {
    this.user = user;
    this.totalPrice = totalPrice;
    this.status = status;
  }
}
