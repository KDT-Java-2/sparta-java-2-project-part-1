
-- user Table

ALTER TABLE user ADD COLUMN role VARCHAR(20) DEFAULT 'BASIC' COMMENT 'ADMIN, MANAGER, BASIC';

-- purchase_product Table
CREATE TABLE cart_product ( -- 단수형으로 이름 변경
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cart_id BIGINT NOT NULL COMMENT '어떤 장바구니에 속하는지',
    product_id BIGINT NOT NULL COMMENT '어떤 상품인지',
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL COMMENT '장바구니 담는 시점의 상품 가격',
    created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);