CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(50) NOT NULL,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      password_hash VARCHAR(255) NOT NULL,
                      gender varchar(2) NOT NULL,
                      phon_number VARCHAR(11),
                      address VARCHAR(255),
                      user_agreement varchar(1) NOT NULL,
                      consent_privacy_policy varchar(1) NOT NULL,
                      created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                      updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Category (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(225) NOT NULL,
                          parent_id BIGINT DEFAULT NULL
);

CREATE TABLE product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT,
                         price DECIMAL(10, 2) NOT NULL,
                         stock INT NOT NULL DEFAULT 0,
                         category_id BIGINT COMMENT '상품이 속한 카테고리 ID',
                         created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                         updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE purchase (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id BIGINT NOT NULL COMMENT '구매한 사용자 ID',
                          total_price DECIMAL(10, 2) NOT NULL,
                          status VARCHAR(20) DEFAULT 'PENDING' COMMENT 'PENDING', 'COMPLETED', 'CANCELED',
    --hipping_address TEXT NOT NULL,
                          created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
                          updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);

CREATE table PurchaseProduct (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 purchase_id BIGINT NOT NULL COMMENT,
                                 product_id BIGINT NOT NULL COMMENT,
                                 created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
                                 updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);