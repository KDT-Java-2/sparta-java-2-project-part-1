package com.sparta.part1.domain.purchase.purchase;

import com.sparta.part1.domain.purchase_product.entity.PurchaseProduct;
import com.sparta.part1.domain.refund.entity.Refund;
import com.sparta.part1.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "purchase")
@Getter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Purchase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false, unique = true)
  String orderNumber;

  BigDecimal totalPrice;
  String status;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(nullable = false)
  LocalDateTime updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  User user;

  @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY)
  List<PurchaseProduct> purchaseProducts;

  @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY)
  List<Refund> refunds;

  public void setStatus(String status) {
    if (StringUtils.hasText(status)) {
      this.status = status;
    }
  }
}
