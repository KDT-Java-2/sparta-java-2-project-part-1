CREATE TABLE refund (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        purchase_id BIGINT NOT NULL,
                        reason VARCHAR(255),
                        status VARCHAR(50),

                        CONSTRAINT fk_refund_purchase FOREIGN KEY (purchase_id) REFERENCES purchase(id)
);
