-- V9: 장바구니에 담긴 개별 상품 항목을 저장하는 테이블
CREATE TABLE cart_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '장바구니 항목 고유 식별자',
    cart_id BIGINT NOT NULL COMMENT '이 항목이 속한 장바구니의 ID',
    product_variant_id BIGINT NOT NULL COMMENT '장바구니에 담은 상품 variant의 ID',
    quantity INT NOT NULL DEFAULT 1 COMMENT '담은 상품의 수량',
    price DECIMAL(19,2) NOT NULL COMMENT '장바구니에 담을 당시의 가격',
    is_checked BOOLEAN NOT NULL DEFAULT TRUE COMMENT '구매 선택 여부(체크박스)',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시각',
    FOREIGN KEY (cart_id) REFERENCES cart(id) ON DELETE CASCADE,
    FOREIGN KEY (product_variant_id) REFERENCES product_variant(id),
    UNIQUE KEY uq_cart_variant (cart_id, product_variant_id)
) COMMENT '장바구니 내 상품 항목'; 