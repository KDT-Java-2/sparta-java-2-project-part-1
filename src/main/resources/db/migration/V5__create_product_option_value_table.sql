-- V5: 옵션 그룹에 속한 실제 값들을 정의하는 테이블 (예: 블랙, L)
CREATE TABLE product_option_value (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '옵션 값 고유 식별자',
    option_group_id BIGINT NOT NULL COMMENT '이 옵션 값이 속한 그룹의 ID',
    value VARCHAR(100) NOT NULL COMMENT '옵션 값 (예: 블랙, L)',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시각',
    FOREIGN KEY (option_group_id) REFERENCES product_option_group(id) ON DELETE CASCADE
) COMMENT '상품 옵션 그룹에 속한 실제 값'; 
