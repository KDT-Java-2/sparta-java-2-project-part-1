package com.dogworld.dogdog.domain.category;

import com.dogworld.dogdog.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  @JsonBackReference
  private Category parent;

  private int depth;

  private int sortOrder;

  @Column(nullable = false)
  private Boolean active;

  @Builder
  public Category(String name, Category parent, int depth, int sortOrder, Boolean active) {
    this.name = name;
    this.parent = parent;
    this.depth = depth;
    this.sortOrder = sortOrder;
    this.active = (active != null) ? active : false;
  }
}
