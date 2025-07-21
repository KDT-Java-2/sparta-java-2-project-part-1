CREATE TABLE purchase_product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id BIGINT NOT NULL COMMENT '어떤 주문에 속하는지',
    product_id BIGINT NOT NULL COMMENT '어떤 상품인지',
    quantity INT NOT NULL COMMENT '수량',
    price DECIMAL(10,2) COMMENT '주문 시점의 상품 가격',
    created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)
);