CREATE TABLE purchase
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id      BIGINT                  NOT NULL,
    total_price  DECIMAL(10, 2)          NOT NULL,
    status       VARCHAR(20),

    buyer_name   VARCHAR(100)             NOT NULL,
    buyer_mobile VARCHAR(20),
    buyer_email  VARCHAR(255),

    is_present TINYINT(1) NOT NULL DEFAULT 0,

    created_at   DATETIME    DEFAULT CURRENT_TIMESTAMP,
    updated_at   DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
