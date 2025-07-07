CREATE TABLE product(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_id BIGINT NOT NULL,
    name BIGINT NOT NULL,
    description VARCHAR(255),
    stock INT,
    status VARCHAR(255),
    image_url VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(category_id) REFERENCES category(id)
);