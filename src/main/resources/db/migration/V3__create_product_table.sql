CREATE TABLE product (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '상품 ID',
    category_id     BIGINT NOT NULL COMMENT '상품이 속한 카테고리 ID FK',
    product_nm      VARCHAR(255) NOT NULL COMMENT '상품명',
    description     TEXT DEFAULT NULL COMMENT '상품설명',
    image_url       VARCHAR(500) DEFAULT NULL COMMENT '상품 대표 이미지',
    price           DECIMAL(10, 2) NOT NULL COMMENT '상품가격',
    discount_price  DECIMAL(10, 2) DEFAULT NULL COMMENT '할인가격',
    stock           INT NOT NULL DEFAULT 0 COMMENT '재고 개수',
    is_active       BOOLEAN DEFAULT TRUE COMMENT '판매 여부',
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
    updated_at      DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);