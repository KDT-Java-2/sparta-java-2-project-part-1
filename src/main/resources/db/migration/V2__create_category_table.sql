CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
        NOT NULL,
    parent_id BIGINT,
    created_at DATETIME
        NOT NULL
        DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME
        NOT NULL
        DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_category_parent
        FOREIGN KEY (parent_id)
        REFERENCES category(id)
);
