CREATE TABLE refund
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT NOT NULL,
    purhase_id  BIGINT NOT NULL,
    reason      TEXT NOT NULL,
    status      VARCHAR(20) NOT NULL ,
    created_at  DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)
);