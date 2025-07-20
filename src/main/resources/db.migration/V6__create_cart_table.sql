CREATE TABLE cart (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      user_id BIGINT NOT NULL,
                      product_id BIGINT NOT NULL,
                      quantity INT NOT NULL,

                      CONSTRAINT fk_cart_user FOREIGN KEY (user_id) REFERENCES user(id),
                      CONSTRAINT fk_cart_product FOREIGN KEY (product_id) REFERENCES product(id)
);
