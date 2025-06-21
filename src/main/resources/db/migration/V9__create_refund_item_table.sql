CREATE TABLE refund_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    refund_id BIGINT NOT NULL,
    purchase_product_id BIGINT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    refund_amount DECIMAL(19,2) NOT NULL,
    reason VARCHAR(500) DEFAULT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (refund_id) REFERENCES refund(id) ON DELETE CASCADE,
    FOREIGN KEY (purchase_product_id) REFERENCES purchase_product(id)
); 