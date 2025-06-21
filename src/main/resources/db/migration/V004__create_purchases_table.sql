CREATE TABLE purchases (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    total_amount DECIMAL(12, 2) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    payment_method VARCHAR(50),
    delivery_address TEXT,
    order_notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    -- 외래 키 제약 조건
    CONSTRAINT fk_purchases_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_purchases_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    
    -- 상태 값 제약 조건
    CONSTRAINT chk_purchases_status CHECK (status IN (
        'PENDING', 'PAYMENT_CONFIRMED', 'PREPARING', 'SHIPPED', 
        'DELIVERED', 'COMPLETED', 'CANCELED', 'REFUND_REQUESTED', 'REFUNDED'
    )),
    
    -- 수량과 금액은 양수여야 함
    CONSTRAINT chk_purchases_quantity CHECK (quantity > 0),
    CONSTRAINT chk_purchases_unit_price CHECK (unit_price >= 0),
    CONSTRAINT chk_purchases_total_amount CHECK (total_amount >= 0)
);

-- 사용자별 구매 내역 조회를 위한 인덱스
CREATE INDEX idx_purchases_user_id ON purchases(user_id);

-- 상품별 구매 통계를 위한 인덱스
CREATE INDEX idx_purchases_product_id ON purchases(product_id);

-- 상태별 구매 내역 조회를 위한 인덱스
CREATE INDEX idx_purchases_status ON purchases(status);

-- 구매 일자별 조회를 위한 인덱스
CREATE INDEX idx_purchases_created_at ON purchases(created_at);

-- 사용자별 최근 구매 내역 조회를 위한 복합 인덱스
CREATE INDEX idx_purchases_user_created ON purchases(user_id, created_at DESC); 