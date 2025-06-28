-- 상품명에 UNIQUE 제약조건 추가
ALTER TABLE products ADD CONSTRAINT uk_products_name UNIQUE (name);

-- price 컬럼을 DECIMAL에서 INT로 변경
ALTER TABLE products MODIFY COLUMN price INT NOT NULL;

-- stock_quantity 컬럼을 stock으로 이름 변경
ALTER TABLE products CHANGE COLUMN stock_quantity stock INT NOT NULL DEFAULT 0;

-- 기존 stock_quantity 인덱스 삭제 후 새로운 stock 인덱스 추가
DROP INDEX idx_products_stock ON products;
CREATE INDEX idx_products_stock ON products(stock); 