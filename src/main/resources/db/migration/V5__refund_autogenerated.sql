CREATE TABLE project_part1.refund
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    created_at  datetime              NOT NULL,
    updated_at  datetime              NOT NULL,
    purchase_id BIGINT                NOT NULL,
    reason      VARCHAR(255)          NULL,
    status      VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_refund PRIMARY KEY (id)
);

ALTER TABLE project_part1.refund
    ADD CONSTRAINT FK_REFUND_ON_PURCHASE FOREIGN KEY (purchase_id) REFERENCES project_part1.purchase (id);