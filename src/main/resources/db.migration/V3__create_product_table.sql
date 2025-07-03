CREATE TABLE product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255),
                         description TEXT,
                         price DECIMAL(15, 2),
                         stock INT,
                         created_at DATETIME NOT NULL,
                         updated_at DATETIME
);
