CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock_quantity INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 상품명 인덱스 추가 (검색 성능 향상)
CREATE INDEX idx_products_name ON products(name);

-- 가격 인덱스 추가 (가격 범위 검색)
CREATE INDEX idx_products_price ON products(price);

-- 재고 인덱스 추가 (재고 관리)
CREATE INDEX idx_products_stock ON products(stock_quantity); 