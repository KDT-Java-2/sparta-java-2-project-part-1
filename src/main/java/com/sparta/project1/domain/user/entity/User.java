package com.sparta.project1.domain.user.entity;

import com.sparta.project1.domain.cart.entity.Cart;
import com.sparta.project1.domain.purchase.entity.Purchase;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.flywaydb.core.internal.util.StringUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "user")
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
    String username;

    @Column(nullable = false) //기본 길이 255
    String email;

    @Column(nullable = false)
    String password;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at")
    LocalDateTime updatedAt;

    //1(사용자) : N(다수구매)
    //기본 EAGER(즉시)라서 LAZY 변경
    //mappedBy의 user는 Purchase쪽 ManyToOne 달아준 필드의 참조변수명을 가리킴
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Purchase> purchases = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Cart> carts = new ArrayList<>();

    @Builder
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void setName(String username) {
        if (StringUtils.hasText(username)) {
            this.username = username;
        }
    }

    public void setEmail(String email) {
        if (StringUtils.hasText(email)) {
            this.email = email;
        }
    }
}
