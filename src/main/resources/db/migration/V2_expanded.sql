CREATE TABLE cart (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      user_id BIGINT NOT NULL,
                      product_id BIGINT NOT NULL,
                      quantity INT NOT NULL,
                      created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                      updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                      CONSTRAINT fk_cart_user FOREIGN KEY (user_id) REFERENCES users(id),
                      CONSTRAINT fk_cart_product FOREIGN KEY (product_id) REFERENCES product(id)
);


CREATE TABLE refund (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        purchase_id BIGINT NOT NULL UNIQUE,
                        reason TEXT NOT NULL,
                        status VARCHAR(20) NOT NULL,
                        created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                        updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        CONSTRAINT fk_refund_purchase FOREIGN KEY (purchase_id) REFERENCES purchase(id)
);