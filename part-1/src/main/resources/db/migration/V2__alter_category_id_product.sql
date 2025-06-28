-- 1. category_id 컬럼 추가
ALTER TABLE product
    ADD COLUMN category_id BIGINT;

-- 2. 외래 키 제약조건 추가
ALTER TABLE product
    ADD CONSTRAINT fk_product_category
        FOREIGN KEY (category_id)
            REFERENCES category (id);
