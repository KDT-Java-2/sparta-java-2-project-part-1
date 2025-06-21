CREATE TABLE refund (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    refund_amount DECIMAL(19, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    reason VARCHAR(255),
    requested_at DATETIME,
    processed_at DATETIME,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,

    CONSTRAINT fk_refund_purchase FOREIGN KEY (purchase_id) REFERENCES purchase (id),
    CONSTRAINT fk_refund_product FOREIGN KEY (product_id) REFERENCES product (id)
);