DROP TABLE cart;
CREATE TABLE cart
(
    id         BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY ,
    user_id    BIGINT                NOT NULL,
    product_id BIGINT                NOT NULL,
    quantity   INT                   NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);