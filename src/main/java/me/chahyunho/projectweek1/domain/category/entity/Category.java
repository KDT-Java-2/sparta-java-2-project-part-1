package me.chahyunho.projectweek1.domain.category.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

  @Setter
  String name;

  @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  @JsonBackReference
  Category parent;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private final List<Category> children = new ArrayList<>(); // 자식 카테고리 목록

  @Setter
  String description;

  @CreationTimestamp
  @Column(updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp // 엔티티 수정시 시간이 자동으로 기록됨
  @Column(nullable = false, updatable = false)
  LocalDateTime updatedAt;

  @PrePersist
  public void onPrePersist() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = this.createdAt;
  }

  @PreUpdate
  public void onPreUpdate() {
    this.updatedAt = LocalDateTime.now();
  }

  @Builder
  public Category(Long id, String name, String description, Category parent) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.parent = parent;
  }
}
