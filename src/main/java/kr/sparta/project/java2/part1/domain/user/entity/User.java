package kr.sparta.project.java2.part1.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import kr.sparta.project.java2.part1.domain.cart.Cart;
import kr.sparta.project.java2.part1.domain.cart.CartItem;
import kr.sparta.project.java2.part1.domain.purchase.entity.Purchase;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false, length = 50)
  String name;

  @Column(nullable = false, unique = true)
  String email;

  @Column(nullable = false)
  String passwordHash;

  @Column(nullable = false)
  String address;

  @Column()
  String addressDetail;

  @Column(nullable = false, length = 10)
  String zipcode;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;

  @Column(nullable = false)
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Column()
  LocalDateTime deletedAt;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  List<Purchase> purchases = new ArrayList<>();

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  List<Cart> carts = new ArrayList<>();

  @Builder
  public User(String name, String email, String passwordHash, String address, String addressDetail,
      String zipcode, LocalDateTime deletedAt) {
    this.name = name;
    this.email = email;
    this.passwordHash = passwordHash;
    this.address = address;
    this.addressDetail = addressDetail;
    this.zipcode = zipcode;
    this.deletedAt = deletedAt;
  }
}
