package com.tommy.ecommerproject.domain.cart.entity;


import com.tommy.ecommerproject.domain.product.entity.Product;
import com.tommy.ecommerproject.domain.purchase.entity.Purchase;
import com.tommy.ecommerproject.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
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
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false, unique = true, length = 36)
  String alias;

  // 유저 정보
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="user_id")
  User user;

  // 장바구니 Product 수량
  @Column(nullable = false)
  @ColumnDefault("0")
  Integer quantity;

  /* 장바구니와 구매 관계 필드 */
  @OneToOne
  @PrimaryKeyJoinColumn
  Purchase purchase;

  @Column
  @CreationTimestamp
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public Cart(
      String alias,
      User user,
      Integer quantity,
      Purchase purchase
  ) {
    this.alias = alias;
    this.user = user;
    this.quantity = quantity;
    this.purchase = purchase;
  }



  @PrePersist
  public void generatedUuid() {
    if(this.alias == null){ // alias가 존재하지 않으면 UUID를 생성하여 String으로 넣어준다.
      this.alias = UUID.randomUUID().toString();
    }
  }

}
