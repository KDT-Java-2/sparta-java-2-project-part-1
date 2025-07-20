package com.sparta.commerce.domain.address.entity;

import com.sparta.commerce.domain.user.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  User user;

  @Column(name = "receiver_name", nullable = false)
  String receiverName;

  @Column(nullable = false)
  String zipcode;

  @Column(name = "road_address", nullable = false)
  String roadAddress;

  @Column(name = "detail_address")
  String detailAddress;

  @Column(name = "is_default", nullable = false)
  Boolean isDefault;

  @Column(name = "created_at", updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public Address(User user, String receiverName, String zipcode, String roadAddress, String detailAddress) {
    this.user = user;
    this.receiverName = receiverName;
    this.zipcode = zipcode;
    this.roadAddress = roadAddress;
    this.detailAddress = detailAddress;
  }

  public void updateIsDefault(boolean isDefault){
    this.isDefault = isDefault;
  }
}
