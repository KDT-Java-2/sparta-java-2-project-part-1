-- V13: 환불 요청에 포함된 개별 상품 내역을 저장하는 테이블
CREATE TABLE refund_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '환불 상품 항목 고유 식별자',
    refund_id BIGINT NOT NULL COMMENT '이 항목이 속한 환불 요청의 ID',
    purchase_product_id BIGINT NOT NULL COMMENT '환불하려는 원본 주문 상품 항목의 ID',
    quantity INT NOT NULL DEFAULT 1 COMMENT '환불하려는 상품의 수량',
    refund_amount DECIMAL(19,2) NOT NULL COMMENT '해당 항목의 환불 금액',
    reason VARCHAR(500) DEFAULT NULL COMMENT '개별 항목 환불 사유 (선택적)',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
    FOREIGN KEY (refund_id) REFERENCES refund(id) ON DELETE CASCADE,
    FOREIGN KEY (purchase_product_id) REFERENCES purchase_product(id)
) COMMENT '환불 상세 내역'; 