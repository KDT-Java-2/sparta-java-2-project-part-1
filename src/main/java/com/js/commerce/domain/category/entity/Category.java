package com.js.commerce.domain.category.entity;

/**
 * 카테고리 엔티티 클래스 - 계층적(트리) 구조를 가지며, 각 카테고리는 상위 카테고리를 가질 수 있습니다. - Jackson JSON 직렬화 시 순환 참조를 방지하기 위해
 * parent 필드는 직렬화 대상에서 제외합니다. Jackson은 Spring MVC(또는 spring-boot-starter-web)가 REST API 응답을 JSON으로
 * 자동 변환할 때 쓰는 라이브러리입니다.
 */

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

  String name;

  /**
   * 상위 카테고리 - LAZY 로딩으로 필요 시에만 조회 - @JsonBackReference: Jackson이 이 필드를 직렬화(객체 -> JSON)하지 않도록 하여 순환
   * 참조를 끊습니다.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  @JsonBackReference
  Category parent;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public Category(
      String name,
      Category parent
  ) {
    this.name = name;
    this.parent = parent;
  }
}
