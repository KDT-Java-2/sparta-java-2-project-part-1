package com.home.sparta_01_commerce.domain.cart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

}
/*
CREATE TABLE cart
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    user_id    BIGINT                NOT NULL,
    product_id BIGINT                NOT NULL,
    quantity   INT                   NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_cart PRIMARY KEY (id)
);
ALTER TABLE cart ADD CONSTRAINT FK_CART_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);
ALTER TABLE cart ADD CONSTRAINT FK_CART_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);
 */