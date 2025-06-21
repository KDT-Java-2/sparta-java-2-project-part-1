CREATE TABLE product (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(255),
     description VARCHAR(255),
     price DECIMAL(19,2),
     stack INT,
     created_at DATETIME
         NOT NULL
         DEFAULT CURRENT_TIMESTAMP,
     updated_at DATETIME
         NOT NULL
         DEFAULT CURRENT_TIMESTAMP
         ON UPDATE CURRENT_TIMESTAMP
);
