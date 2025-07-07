-- 1. 기존 purchase 테이블의 status 컬럼 제거
ALTER TABLE purchase
DROP COLUMN status;

-- 2. purchase_product 테이블에 status 컬럼 추가 (ENUM 대신 VARCHAR)
ALTER TABLE purchase_product
ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'ORDERED' COMMENT 'ORDERED, PREPARING, SHIPPED, DELIVERED, CANCELED, RETURNED, REFUNDED';
