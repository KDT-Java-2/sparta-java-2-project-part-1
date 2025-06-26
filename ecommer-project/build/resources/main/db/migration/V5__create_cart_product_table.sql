
-- Cart <-> Product 중간테이블

CREATE TABLE cart_product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY ,

    cart_id BIGINT NOT NULL ,
    product_id BIGINT NOT NULL ,

    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
)