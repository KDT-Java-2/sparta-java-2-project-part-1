CREATE TABLE cart
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT                NOT NULL,
    quantity   INT                   NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NULL
);

CREATE TABLE category
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255)          NULL,
    parent_id  BIGINT                NULL,
    created_at datetime              NULL,
    updated_at datetime              NULL
);

CREATE TABLE product
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(255)          NULL,
    `description` VARCHAR(255)          NULL,
    price         DECIMAL               NULL,
    stock         INT                   NULL,
    created_at    datetime              NOT NULL,
    updated_at    datetime              NULL
);

CREATE TABLE purchase
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT                NOT NULL,
    total_price DECIMAL               NULL,
    status      VARCHAR(20)           NOT NULL,
    created_at  datetime              NOT NULL,
    updated_at  datetime              NULL
);

CREATE TABLE purchase_product
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id BIGINT                NULL,
    product_id  BIGINT                NULL,
    created_at  datetime              NOT NULL
);
CREATE TABLE user
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    username      VARCHAR(50)           NOT NULL,
    email         VARCHAR(255)          NOT NULL,
    password_hash VARCHAR(255)          NOT NULL,
    created_at    datetime              NOT NULL,
    updated_at    datetime              NULL
);