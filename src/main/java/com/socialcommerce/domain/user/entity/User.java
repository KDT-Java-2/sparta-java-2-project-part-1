package com.socialcommerce.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.socialcommerce.common.enums.Gender;
import com.socialcommerce.domain.cart.entity.Cart;
import com.socialcommerce.domain.purchase.entity.Purchase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
@Table
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor  // 빈생성자를 만들어준다. 항상 있는데 귀찮기 때문에
@FieldDefaults(level = AccessLevel.PRIVATE) // 모든 접근 제한이 private 으로 바뀐다.
//@Table(name = "user") // 테이블명(db.migration 안에있는 .sql 안에 테이블명..)과 클래스명이 완전히 같으면 생략이 가능
public class User {

  // 생성방식이 IDENTITY 면 sql 안에 id BIGINT AUTO_INCREMENT PRIMARY KEY 의 AUTO_INCREMENT 를 따라간다.
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false, length = 50)
  String nickName;
  // 실제 테이블상의 컬럼명 알려줘야함, VARCHAR(50) == lenght 50
  @Column(nullable = false, length = 50)
  String name;
  @Column(nullable = false, unique = true)
  String email;
  @Column(nullable = false)
  LocalDate dateOfBirth;
  @Enumerated(EnumType.STRING)
  @Column(length = 10)
  Gender gender; // 'MALE', 'FEMALE', 'NONE' 등
  @Column(nullable = false, length = 20)
  String phoneNumberHash;

  // 카멜표기로 제대로 되어있으면 언더바를 대문자로 인식한다. 그래서 JPA 에서 알아서 인식해주므로 생략가능하다.
  //@Column(name = "password_hash")
  @Column(nullable = false)
  String passwordHash;

  // 1:N
  @OneToMany(mappedBy = "user")
  @JsonManagedReference
  List<Cart> carts;

  // User 가 여러개의 Purchase 를 가지고 있다 = 1:N 관계
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  @JsonManagedReference
  List<Purchase> purchases = new ArrayList<>();

  @Column(nullable = false, updatable = false)  // updatable = false 수정 불가능
  @CreationTimestamp  // CURRENT_TIMESTAMP 와 동일
  LocalDateTime createdAt;
  @Column
  @UpdateTimestamp  // UPDATE_TIMESTAMP 와 동일
  LocalDateTime updatedAt;

  // 빌더패턴은 DataClass 를 객체선언해서 값을 올려주려고 할때, 직관적이고 좋다.
  // User 클래스 자체에 Builder 를 붙여도 사용할 수는 있지만, 모든 필드에 값을 넣을수 있게 한다.
  // Builder 는 AllArgsConstructor 를 강요하기때문에 값을 넣어줄때,
  // id 라던가 LocalDateTime 같은 자동으로 DB 에서 생성하는것에도 임의로 넣어주어야한다.
  // public User 아규먼트 생성자쪽에 붙여주면 실제로 사용할때 모든 필드에 값을 넣어주지 않아도 된다.

@Builder
  public User(
      String name,
      String nickName,
      String email,
      LocalDate dateOfBirth,
      Gender gender,
      String phoneNumberHash,
      String passwordHash
  ) {
    this.name = name;
    this.nickName = nickName;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.phoneNumberHash = phoneNumberHash;
    this.passwordHash = passwordHash;

  }
}
