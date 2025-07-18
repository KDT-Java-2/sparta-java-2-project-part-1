package com.sparta.commerce.domain.category.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Builder
  public Category(
      String name,
      String description,
      Category parent,
      Integer depth) {
    this.name = name;
    this.description = description;
    this.parent = parent;
    // depth가 null이거나 음수일 경우 기본값 0으로 설정
    this.depth = (depth != null && depth >= 0) ? depth : 0;
  }

  @Column(nullable = false, length = 50)
  String name; // 카테고리 이름

  @Column
  String description; // 카테고리 설명, 선택적 필드

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  @JsonBackReference
  Category parent; // 상위 카테고리

  @Column(nullable = false, updatable = false)
  Integer depth = 0; // 카테고리 깊이, 기본값 0

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  /**
   * 카테고리 이름을 업데이트하는 메서드
   * @param newName 새로운 카테고리 이름
   */
  public void updateName(String newName) {
    if (newName != null && !newName.trim().isEmpty()) {
      this.name = newName;
    }
  }

  /**
   * 상위 카테고리를 설정하고 깊이를 재계산하는 메서드
   * @param newParent 새로운 상위 카테고리
   */
  public void setParent(Category newParent) {
    this.parent = newParent;
    if (newParent != null) {
      this.depth = newParent.getDepth() + 1;
    } else {
      this.depth = 0; // 최상위 카테고리인 경우
    }
  }

  public void setName(String name) {
    if (name != null && !name.trim().isEmpty()) {
      this.name = name;
    }
  }

  public void setDescription(String description) {
    if(description != null && !description.trim().isEmpty()) {
      this.description = description;
    }

  }
}
