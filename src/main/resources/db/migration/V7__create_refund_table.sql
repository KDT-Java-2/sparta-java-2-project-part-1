CREATE TABLE refund
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id     BIGINT         NOT NULL,
    product_id      BIGINT         NOT NULL,

    cancel_quantity INT            NOT NULL,
    refund_amount   DECIMAL(10, 2) NOT NULL,
    reason          VARCHAR(255),

    status          VARCHAR(20),

    created_at      DATETIME    DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
