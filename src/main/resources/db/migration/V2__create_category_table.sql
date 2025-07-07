-- V2: 상품 카테고리를 관리하는 테이블
CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '카테고리 고유 식별자',
    name VARCHAR(255) NOT NULL COMMENT '카테고리 이름',
    parent_id BIGINT DEFAULT NULL COMMENT '상위 카테고리 ID (계층 구조용)',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시각',
    FOREIGN KEY (parent_id) REFERENCES category(id)
) COMMENT '상품 카테고리'; 