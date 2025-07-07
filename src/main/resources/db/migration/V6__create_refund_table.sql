CREATE TABLE refund
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    purchase_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    reason VARCHAR(255),
    status VARCHAR(255),
    requested_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    processed_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(user_id) REFERENCES user(id),
    FOREIGN KEY(purchase_id) REFERENCES purchase(id),
    FOREIGN KEY(product_id) REFERENCES product(id)
);