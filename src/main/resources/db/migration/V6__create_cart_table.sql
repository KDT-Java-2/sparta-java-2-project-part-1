CREATE TABLE cart (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '장바구니 ID',
    user_id         BIGINT NOT NULL COMMENT '사용자 ID',
    product_id      BIGINT NOT NULL COMMENT '상품 ID',
    quantity        INT NOT NULL COMMENT '장바구니에 담은 상품 개수',
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
    UNIQUE KEY uq_cart_user_product (user_id, product_id)
);