ALTER TABLE refunds
    DROP FOREIGN KEY refunds_ibfk_1;
ALTER TABLE refunds
    DROP INDEX purchase_id;
ALTER TABLE refunds
    CHANGE COLUMN purchase_id purchase_product_id BIGINT NOT NULL;
ALTER TABLE refunds
    ADD CONSTRAINT fk_refund_purchase_product
        FOREIGN KEY (purchase_product_id) REFERENCES purchase_product (id);
