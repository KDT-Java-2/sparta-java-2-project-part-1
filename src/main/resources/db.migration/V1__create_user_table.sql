CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(50) NOT NULL,
                      email VARCHAR(255),
                      password_hash VARCHAR(255),
                      created_at DATETIME NOT NULL,
                      updated_at DATETIME
);
