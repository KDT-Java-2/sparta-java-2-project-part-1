ALTER TABLE option_group
ADD COLUMN product_id BIGINT NOT NULL,
ADD CONSTRAINT fk_option_group_product
FOREIGN KEY (product_id) REFERENCES product(id)
ON DELETE CASCADE;
