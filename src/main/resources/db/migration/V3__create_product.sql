CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '상품 고유 ID',
    name VARCHAR(50) NOT NULL COMMENT '상품명',
    description TEXT COMMENT '상품 설명',
    price DECIMAL(10,2) NOT NULL COMMENT '가격',
    stock INT NOT NULL DEFAULT 0 COMMENT '재고 수량',
    category_id BIGINT COMMENT '카테고리 ID',
    image_url VARCHAR(255) COMMENT '대표 이미지 경로',
    status VARCHAR(20) COMMENT '상품 상태',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
