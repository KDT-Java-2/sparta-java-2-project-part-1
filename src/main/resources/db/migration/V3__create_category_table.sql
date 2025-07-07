CREATE TABLE category(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    parent_id BIGINT,
    sort_order INT,
    FOREIGN KEY(parent_id) REFERENCES category(id)
);