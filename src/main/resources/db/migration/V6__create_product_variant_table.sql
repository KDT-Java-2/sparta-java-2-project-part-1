-- V6: 상품의 개별 판매 단위(SKU) 정보를 저장하는 테이블
CREATE TABLE product_variant (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '상품 variant 고유 식별자',
    product_id BIGINT NOT NULL COMMENT '이 variant가 속한 상품의 ID',
    price DECIMAL(19,2) NOT NULL COMMENT '옵션 조합별 최종 판매 가격',
    stock INT NOT NULL COMMENT '옵션 조합별 현재 재고',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시각',
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
) COMMENT '실제 가격과 재고를 가진 개별 판매 상품'; 