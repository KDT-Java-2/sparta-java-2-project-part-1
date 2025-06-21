CREATE TABLE product
(
    id          BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT,
    name        VARCHAR(255),
    description TEXT,
    price       DECIMAL(10, 2),
    stock       INT,
    created_at  DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at  DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)
);