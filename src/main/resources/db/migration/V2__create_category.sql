-- category
CREATE TABLE category
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL UNIQUE,
    parent_id  BIGINT   DEFAULT NULL COMMENT '부모 카테고리 ID (자기 참조)',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (parent_id) REFERENCES category (id) ON DELETE SET NULL
);


ALTER TABLE purchase
    ADD COLUMN shipping_address TEXT NOT NULL;

ALTER TABLE product
    ADD COLUMN category_id BIGINT COMMENT '상품이 속한 카테고리 ID',
    ADD CONSTRAINT fk_product_category
        FOREIGN KEY (category_id) REFERENCES category (id);
