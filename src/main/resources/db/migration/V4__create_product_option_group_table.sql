-- V4: 상품별 옵션 그룹을 정의하는 테이블 (예: 색상, 사이즈)
CREATE TABLE product_option_group (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '옵션 그룹 고유 식별자',
    product_id BIGINT NOT NULL COMMENT '이 옵션 그룹이 속한 상품의 ID',
    name VARCHAR(100) NOT NULL COMMENT '옵션 그룹명 (예: 색상)',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시각',
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
) COMMENT '상품별 옵션 종류(그룹)'; 