CREATE TABLE purchase_product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id BIGINT
        NOT NULL,
    product_id BIGINT
        NOT NULL,
    quantity INT
        NOT NULL,
    price_at_order DECIMAL(19,2)
        NOT NULL,
    item_option VARCHAR(100),
    created_at DATETIME
        NOT NULL
        DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_pp_purchase
        FOREIGN KEY (purchase_id)
        REFERENCES purchase(id),
    CONSTRAINT fk_pp_product
        FOREIGN KEY (product_id)
        REFERENCES product(id)
);
