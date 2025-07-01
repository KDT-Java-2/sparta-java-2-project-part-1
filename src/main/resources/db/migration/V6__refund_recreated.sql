DROP TABLE refund;
CREATE TABLE refund
(
    id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY ,
    purchase_id BIGINT                NOT NULL,
    reason      VARCHAR(255)          NULL,
    status      VARCHAR(255)          NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
