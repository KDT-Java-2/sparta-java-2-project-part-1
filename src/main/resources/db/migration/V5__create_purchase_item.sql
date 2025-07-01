CREATE TABLE purchase_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id BIGINT NOT NULL comment '주문 ID',
    product_id BIGINT NOT NULL comment '상품 ID',
    quantity INT NOT NULL comment '구매 수량',
    price DECIMAL(10,2) NOT NULL comment '단가',
    total_price DECIMAL(10,2) NOT NULL comment '수량x단가',
    status varchar(20) comment '출고 상태',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);