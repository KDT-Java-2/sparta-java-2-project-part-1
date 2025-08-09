-- V5__create_purchase_product_table.sql

CREATE TABLE IF NOT EXISTS purchase_product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(19,2) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_purchase_product_purchase FOREIGN KEY (purchase_id) REFERENCES purchase(id),
    CONSTRAINT fk_purchase_product_product FOREIGN KEY (product_id) REFERENCES product(id)
);
