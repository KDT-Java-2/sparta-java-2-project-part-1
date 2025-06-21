CREATE TABLE refunds (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 purchase_id BIGINT NOT NULL UNIQUE,
 reason VARCHAR(255),
 status VARCHAR(50) NOT NULL,

 CONSTRAINT fk_refund_purchase FOREIGN KEY (purchase_id) REFERENCES purchase(id)
);
