CREATE TABLE purchase_product
(
    id          BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    purchase_id BIGINT NOT NULL,
    product_id  BIGINT NOT NULL,
    created_at  DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)
);