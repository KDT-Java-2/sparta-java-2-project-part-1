package com.tommy.ecommerproject.domain.purchase.entity;


import com.tommy.ecommerproject.common.enums.PurchaseStatusType;
import com.tommy.ecommerproject.domain.user.entity.User;
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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Purchase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false, unique = true, length = 36)
  String alias;

  /* 사용자와 관련된 필드 */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  User user;

  /* 주문정보와 관련된 필드 */
  @Column(nullable = false)
  String address;       // 주문 주소

  @Column(nullable = false)
  String phoneNumber;   // 주문자 핸드폰 번호

  @Column(nullable = false)
  String comment;       // 주문 요청사항

  @Column
  BigDecimal totalPrice;

  @Column(nullable = false, length=20)
  @Enumerated(EnumType.STRING)
  @ColumnDefault("PENDDING")
  PurchaseStatusType status;    // 주문상태

  @Column
  @CreationTimestamp
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public Purchase(String alias, User user, String address, String phoneNumber, String comment,
      BigDecimal totalPrice, PurchaseStatusType status) {
    this.alias = alias;
    this.user = user;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.comment = comment;
    this.totalPrice = totalPrice;
    this.status = status;
  }

  @PrePersist
  public void generatedUuid() {
    if(this.alias == null){ // alias가 존재하지 않으면 UUID를 생성하여 String으로 넣어준다.
      this.alias = UUID.randomUUID().toString();
    }
  }

}
