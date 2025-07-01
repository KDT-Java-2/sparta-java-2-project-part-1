CREATE TABLE cart (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL comment '사용자 ID',
    product_id BIGINT NOT NULL comment '상품 ID',
    quantity INT NOT NULL comment '수량',
    added_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);