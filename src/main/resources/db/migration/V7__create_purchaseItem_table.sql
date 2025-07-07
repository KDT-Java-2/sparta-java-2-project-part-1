CREATE TABLE purchaseItem
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    price DECIMAL(10,2),
    quantity INT,
    FOREIGN KEY(purchase_id) REFERENCES purchase(id),
    FOREIGN KEY(product_id) REFERENCES product(id)
);