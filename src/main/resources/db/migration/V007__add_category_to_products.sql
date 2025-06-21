-- products 테이블에 category_id 컬럼 추가
ALTER TABLE products 
ADD COLUMN category_id BIGINT NULL;

-- 외래 키 제약 조건 추가
ALTER TABLE products 
ADD CONSTRAINT fk_products_category 
FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL;

-- 카테고리별 상품 조회를 위한 인덱스 추가
CREATE INDEX idx_products_category_id ON products(category_id); 