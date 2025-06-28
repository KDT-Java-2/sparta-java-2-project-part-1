CREATE TABLE cart
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT         NOT NULL,
    product_id  BIGINT         NOT NULL,
    quantity    INT            NOT NULL,
    created_at  DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)
);