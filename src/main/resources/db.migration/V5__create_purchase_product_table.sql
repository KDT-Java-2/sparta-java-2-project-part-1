CREATE TABLE purchase_product (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  purchase_id BIGINT NOT NULL,
                                  product_id BIGINT NOT NULL,
                                  created_at DATETIME NOT NULL,

                                  CONSTRAINT fk_purchase_product_purchase FOREIGN KEY (purchase_id) REFERENCES purchase(id),
                                  CONSTRAINT fk_purchase_product_product FOREIGN KEY (product_id) REFERENCES product(id)
);
