package com.sparta.commerce.domain.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OptionItem {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "option_group_id", nullable = false)
  OptionGroup optionGroup;

  @Column(nullable = false, length = 100)
  String value; // 예: 검정색, 260, M, L

  @Column(name = "additional_price", nullable = false)
  BigDecimal additionalPrice;

  @CreationTimestamp
  LocalDateTime createdAt;

  @Builder
  public OptionItem(OptionGroup optionGroup, String value, BigDecimal additionalPrice) {
    this.optionGroup = optionGroup;
    this.value = value;
    this.additionalPrice = additionalPrice;
  }
}
