package com.socialcommerce.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class BaseDeletableEntity {

  @Column(nullable = false)
  protected boolean deleted = false;

  public void softDelete() {
    this.deleted = true;
  }

  public boolean isDeleted() {
    return this.deleted;
  }
}
