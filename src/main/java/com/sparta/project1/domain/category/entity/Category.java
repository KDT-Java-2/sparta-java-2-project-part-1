package com.sparta.project1.domain.category.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.project1.domain.product.entity.Product;
import jakarta.persistence.*;

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

@Table(name = "category")
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @OneToMany(mappedBy = "category")
  List<Product> products = new ArrayList<>();

  @Column(nullable = false)
  String name;

  String description;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  Category parent;

  @OneToMany(mappedBy = "parent")
  private List<Category> children = new ArrayList<>();

  @CreationTimestamp
  @Column(updatable = false, name = "created_at")
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(nullable = false, name = "updated_at")
  LocalDateTime updatedAt;

  @Builder
  public Category(
      String name,
      String description,
      Category parent
  ) {
    this.name = name;
    this.description = description;
    this.parent = parent;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setParent(Category parent) {
    this.parent = parent;
  }
}