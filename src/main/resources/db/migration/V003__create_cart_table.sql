CREATE TABLE cart (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    -- 외래 키 제약 조건
    CONSTRAINT fk_cart_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_cart_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    
    -- 동일한 사용자가 같은 상품을 중복으로 장바구니에 담는 것을 방지
    UNIQUE KEY uk_cart_user_product (user_id, product_id)
);

-- 사용자별 장바구니 조회 성능 향상을 위한 인덱스
CREATE INDEX idx_cart_user_id ON cart(user_id);

-- 상품별 장바구니 담긴 횟수 조회를 위한 인덱스
CREATE INDEX idx_cart_product_id ON cart(product_id);

-- 생성일자 인덱스 (최근 담긴 상품 조회)
CREATE INDEX idx_cart_created_at ON cart(created_at); 