
DROP TABLE purchase_product;
DROP TABLE product;
DROP TABLE category;
DROP TABLE purchase;
DROP TABLE user;
-- user Table
CREATE TABLE user (
                      id                       BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                      name                     VARCHAR(50)           NOT NULL,
                      email                    VARCHAR(255)          NOT NULL UNIQUE ,
                      password_hash            VARCHAR(255)          NOT NULL,
                      gender                   CHAR                  NOT NULL,
                      birth_day                datetime              NULL,
                      phone_number             VARCHAR(11)           NOT NULL,
                      postal_code              VARCHAR(5)            NULL,
                      address                  VARCHAR(255)          NULL,
                      profile_image_url        VARCHAR(255)          NULL,
                      personal_info_consent_yn CHAR                  NULL,
                      created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                      updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
-- purchase Table
CREATE TABLE purchase (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id BIGINT NOT NULL COMMENT '구매한 사용자 ID',
                          total_price DECIMAL(10, 2) NOT NULL,
                          status VARCHAR(20) DEFAULT 'PENDING' COMMENT 'PENDING, COMPLETED, CANCELED',
                          shipping_address TEXT NOT NULL,
                          created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
                          updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);
-- category Table
CREATE TABLE category (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          parent_id BIGINT DEFAULT NULL COMMENT '부모 카테고리 ID (자기 참조)',
                          created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                          updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


-- product Table
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
CREATE TABLE purchase_product (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              purchase_id BIGINT NOT NULL COMMENT '어떤 주문에 속하는지',
                              product_id BIGINT NOT NULL COMMENT '어떤 상품인지',
                              quantity INT NOT NULL,
                              price DECIMAL(10, 2) NOT NULL COMMENT '주문 시점의 상품 가격',
                              created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
                              updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);
