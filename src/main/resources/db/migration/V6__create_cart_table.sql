CREATE TABLE cart (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT
        NOT NULL,
    CONSTRAINT fk_cart_user
        FOREIGN KEY (user_id)
        REFERENCES user(id)
);