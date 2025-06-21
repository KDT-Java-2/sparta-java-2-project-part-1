-- 사용자 테이블
CREATE TABLE user (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      password_hash VARCHAR(255) NOT NULL,
      name VARCHAR(100) NOT NULL,
      address VARCHAR(500),
      phone_number VARCHAR(20),
      is_active BOOLEAN DEFAULT TRUE,
      level INT DEFAULT 1,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 상품 테이블
CREATE TABLE product (
         id BIGINT AUTO_INCREMENT PRIMARY KEY,
         name VARCHAR(200) NOT NULL,
         sub_name VARCHAR(200),
         stock INT DEFAULT 0,
         category_id BIGINT,
         img_url VARCHAR(500),
         description TEXT,
         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 장바구니 테이블
CREATE TABLE cart (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      user_id BIGINT NOT NULL,
      product_id BIGINT NOT NULL,
      quantity INT DEFAULT 1,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 인덱스 추가
CREATE INDEX idx_user_phone ON user(phone_number);
CREATE INDEX idx_product_category ON product(category_id);
CREATE INDEX idx_cart_user ON cart(user_id);
CREATE INDEX idx_cart_product ON cart(product_id);
