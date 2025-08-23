CREATE TABLE purchase_product
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id BIGINT                        NOT NULL,
    product_id  BIGINT                        NOT NULL,

    status      VARCHAR(20),

    quantity    INT                           NOT NULL,
    sell_price  DECIMAL(10, 2)                NOT NULL,
    buy_price   DECIMAL(10, 2)                NOT NULL,
    total_price DECIMAL(10, 2)                NOT NULL,

    created_at  DATETIME    DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
