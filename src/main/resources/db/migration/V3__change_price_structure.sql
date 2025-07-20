ALTER TABLE product
ADD COLUMN base_price DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '기본 가격';

ALTER TABLE option_item
ADD COLUMN additional_price DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '옵셜별 추가 가격';

ALTER TABLE product_item
DROP COLUMN price;
