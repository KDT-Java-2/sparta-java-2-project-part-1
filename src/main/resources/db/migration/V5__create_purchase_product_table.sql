CREATE TABLE purchase_product (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '상품 구매 ID',
    purchase_id BIGINT NOT NULL COMMENT '구매 ID FK',
    product_id  BIGINT NOT NULL COMMENT '상품 ID FK',
    quantity    INT NOT NULL COMMENT '개수',
    price       DECIMAL(10, 2) NOT NULL COMMENT '구매 당시 가격',
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '등록일'
);