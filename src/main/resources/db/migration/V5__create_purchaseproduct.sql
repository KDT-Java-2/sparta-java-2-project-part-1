CREATE TABLE purchase_product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id BIGINT NOT NULL COMMENT '어떤 주문에 속하는지',
    product_id BIGINT NOT NULL COMMENT '어떤 상품인지',
    created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)
);