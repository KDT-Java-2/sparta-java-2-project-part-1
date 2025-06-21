CREATE TABLE purchase_product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id BIGINT NOT NULL COMMENT '어떤 주문에 속하는지',
    product_id BIGINT NOT NULL COMMENT '어떤 상품인지',
    quantity INT NOT NULL COMMENT '수량',
    price DECIMAL(10, 6) NOT NULL COMMENT '주문 시점의 상품 가격',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_purchase_product__purchase
        FOREIGN KEY (purchase_id) REFERENCES purchase(id),

    CONSTRAINT fk_purchase_product__product
        FOREIGN KEY (product_id) REFERENCES product(id)
);
