CREATE TABLE project_part1.cart
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NOT NULL,
    user_id    BIGINT                NOT NULL,
    product_id BIGINT                NOT NULL,
    quantity   INT                   NOT NULL,
    CONSTRAINT pk_cart PRIMARY KEY (id)
);

ALTER TABLE project_part1.cart
    ADD CONSTRAINT FK_CART_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES project_part1.product (id);

ALTER TABLE project_part1.cart
    ADD CONSTRAINT FK_CART_ON_USER FOREIGN KEY (user_id) REFERENCES project_part1.user (id);