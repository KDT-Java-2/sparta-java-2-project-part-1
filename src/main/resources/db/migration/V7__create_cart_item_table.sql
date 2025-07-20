CREATE TABLE cart_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cart_id BIGINT
        NOT NULL,
    product_id BIGINT
        NOT NULL,
    qauntity INT
        NOT NULL,
    price_at_add DECIMAL(19,2)
        NOT NULL,
    item_option VARCHAR(100),
    CONSTRAINT fk_cart_item_cart
        FOREIGN KEY (cart_id)
        REFERENCES cart(id),
    CONSTRAINT fk_cart_item_product
        FOREIGN KEY (product_id)
        REFERENCES product(id)
);