package com.js.commerce.domain.purchase.entity;

import com.js.commerce.common.enums.PurchaseStatus;
import com.js.commerce.domain.user.entity.User;
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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.ObjectUtils;

@Table
@Entity
//@Data  // 필드 전체에 getter, setter를 만들어줌. 부가적으로 불필요한 것들도 딸려오므로 매우 제한적으로 사용할 것
@Getter  // Getter는 클래스에, Setter는 제한적으로 필요한 필드에만(웬만해선 롬복으로 하지 말고 직접 메소드를 만들 것!)
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Purchase {  // 주문

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  User user;

  @Enumerated(EnumType.STRING)  // ENUM을 그냥 사용하면 1,2,3,4... 숫자값으로 들어가서 바람직하지 않음
  @Column(nullable = false, length = 20)  // nullable 안 써도 테이블에서 먼저 터지긴 하지만 nullable 명시해서 관리해줘야 함
  PurchaseStatus status;  // 개발자가 로직에만 사용하는 상태값은 Enum으로 코드로! 실제 테이블 컬럼에선 varchar(20) 임!

  @Column
//  @Column(name = "total_price") // 이름 동일하니까 생략 가능 (스네이크, 카멜은 알아서 인식해줌)
  BigDecimal totalPrice;  // 미국 같은 나라는 가격에 소수점에 붙음. 돈 관련된 건 무조건 BigDecimal로!

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

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

  public void setStatus(PurchaseStatus status) {
    if (!ObjectUtils.isEmpty(status)) {
      this.status = status;
    }
  }

}
