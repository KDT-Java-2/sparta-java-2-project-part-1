package com.sparta.ecommerce.domain.category.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.ecommerce.domain.category.dto.CategoryCreateRequest;
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

@Entity
@Table
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  @JsonBackReference
  private Category parent;

  @Column(nullable = false, length = 50)
  private String categoryNm;

  @Column(length = 255)
  private String description;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column
  LocalDateTime updatedAt;

  @OneToMany(mappedBy = "parent")
  private List<Category> children = new ArrayList<>();

  @Builder
  public Category(Category parent, String categoryNm, String description) {
    this.parent = parent;
    this.categoryNm = categoryNm;
    this.description = description;
  }

  public void update(CategoryCreateRequest request, Category parent) {
    this.categoryNm = request.getName();
    this.description = request.getDescription();
    this.parent = parent;
  }
}