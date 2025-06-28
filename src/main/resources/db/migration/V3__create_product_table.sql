-- V3: 상품의 기본 정보를 저장하는 테이블
CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '상품 고유 식별자',
    name VARCHAR(255) DEFAULT NULL COMMENT '상품 이름',
    description TEXT DEFAULT NULL COMMENT '상품 상세 설명',
    category_id BIGINT DEFAULT NULL COMMENT '상품이 속한 카테고리 ID',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시각',
    FOREIGN KEY (category_id) REFERENCES category(id)
) COMMENT '상품 기본 정보'; 