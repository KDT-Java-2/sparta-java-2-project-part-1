-- V11: 주문에 포함된 개별 상품 내역을 저장하는 테이블 (영수증 상세)
CREATE TABLE purchase_product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '주문 상품 항목 고유 식별자',
    purchase_id BIGINT NOT NULL COMMENT '이 항목이 속한 주문의 ID',
    product_variant_id BIGINT NOT NULL COMMENT '주문된 상품 variant의 ID',
    quantity INT NOT NULL DEFAULT 1 COMMENT '주문된 상품의 수량',
    price_at_purchase DECIMAL(19,2) NOT NULL COMMENT '주문 시점의 상품 단가 (가격 변동 추적용)',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
    FOREIGN KEY (purchase_id) REFERENCES purchase(id),
    FOREIGN KEY (product_variant_id) REFERENCES product_variant(id)
) COMMENT '주문 상세 내역'; 