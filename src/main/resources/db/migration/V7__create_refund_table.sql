CREATE TABLE refund (
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '환불 ID',
    purchase_id         BIGINT NOT NULL COMMENT '구매 ID',
    product_id          BIGINT DEFAULT NULL COMMENT '환불 상품 ID (부분 환불일 경우)',
    quantity            INT DEFAULT NULL COMMENT '환불 수량',
    reason              VARCHAR(255) NOT NULL COMMENT '환불 사유',
    refund_status       VARCHAR(20) DEFAULT 'REQUESTED'
        COMMENT '환불상태 : REQUESTED, APPROVED, PROCESSING, COMPLETED, REJECTED, FAILED',
    created_at          DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT '등록일',
    updated_at          DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '수정일'
);

