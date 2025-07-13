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
import org.springframework.util.StringUtils;

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

  @Column(nullable = false)
  String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  @JsonBackReference
  Category parent;

  @Column
  String description;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
  public Category(String name, Category parent, String description) {
    this.name = name;
    this.parent = parent;
    this.description = description;
  }

  public void setName(String name) {
    if (StringUtils.hasText(name)) {
      this.name = name;
    }
  }

  public void setParent(Category parent) {
    if (!StringUtils.isEmpty(parent)) {
      this.parent = parent;
    } else {
      this.parent = null;
    }
  }

  public void setDescription(String description) {
    if (StringUtils.hasText(description)) {
      this.description = description;
    }
  }

}
