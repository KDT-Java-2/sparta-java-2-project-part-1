package com.example.shoppingmall.domain.product.entity;

import com.example.shoppingmall.domain.user.entity.User;
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

@Table(name = "product_qna")
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductQna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    ProductQna parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    List<ProductQna> children = new ArrayList<>();

    @Column(columnDefinition = "TEXT", nullable = false)
    String question;

    @Column(columnDefinition = "TEXT")
    String answer;

    @Column(nullable = false)
    Boolean isPrivate = false;

    @Column
    LocalDateTime answeredAt;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    LocalDateTime updatedAt;

    @Builder
    public ProductQna(Product product, User user, ProductQna parent, String question, String answer, Boolean isPrivate, LocalDateTime answeredAt) {
        this.product = product;
        this.user = user;
        this.parent = parent;
        this.question = question;
        this.answer = answer;
        this.isPrivate = isPrivate;
        this.answeredAt = answeredAt;
    }

    public void update(String question, String answer, Boolean isPrivate, LocalDateTime answeredAt) {
        this.question = question;
        this.answer = answer;
        this.isPrivate = isPrivate;
        this.answeredAt = answeredAt;
    }
} 