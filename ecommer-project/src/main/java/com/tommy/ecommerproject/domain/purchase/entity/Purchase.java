package com.tommy.ecommerproject.domain.purchase.entity;


import com.tommy.ecommerproject.common.enums.PurchaseStatusType;
import com.tommy.ecommerproject.domain.cart.entity.Cart;
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
import jakarta.persistence.OneToOne;
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

  /* 주문정보와 관련된 필드 */

  @Column(nullable = false)
  String address;       // 주문 주소

  @Column(nullable = false, length = 13)
  String phoneNumber;   // 주문자 핸드폰 번호

  @Column(nullable = false)
  String comment;       // 주문 요청사항

  @Column(nullable = false)
  BigDecimal totalPrice;

  @Column(nullable = false, length=20)
  @Enumerated(EnumType.STRING)
  @ColumnDefault("PENDDING")
  PurchaseStatusType status;    // 주문상태


  /* 사용자와 관련된 필드 */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  User user;

  /* 장바구니와 관련된 필드 */
  @OneToOne(fetch = FetchType.EAGER)  // 주문이 만들어지면 Cart의 내역을 불어와야 하기 때문에 EAGER
  @JoinColumn(name = "cart_id")
  Cart cart;

  @Column
  @CreationTimestamp
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public Purchase(
      String alias,
      String address,
      String phoneNumber,
      String comment,
      BigDecimal totalPrice,
      PurchaseStatusType status,
      User user,
      Cart cart
  ) {
    this.alias = alias;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.comment = comment;
    this.totalPrice = totalPrice;
    this.status = status;
    this.user = user;
    this.cart = cart;
  }

  @PrePersist
  public void generatedUuid() {
    if(this.alias == null){ // alias가 존재하지 않으면 UUID를 생성하여 String으로 넣어준다.
      this.alias = UUID.randomUUID().toString();
    }
  }


  /* PR 위한 주석 */

}
