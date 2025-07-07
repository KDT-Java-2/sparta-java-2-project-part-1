-- V12: 환불 요청 정보를 관리하는 테이블
CREATE TABLE refund (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '환불 고유 식별자',
    purchase_id BIGINT NOT NULL UNIQUE COMMENT '환불 대상이 되는 주문의 ID (주문당 하나의 환불만 가능)',
    reason VARCHAR(500) NOT NULL COMMENT '환불 요청 사유 (전체 환불 사유)',
    status VARCHAR(20) NOT NULL COMMENT '환불 처리 상태 (예: REQUESTED, APPROVED, REJECTED, COMPLETED)',
    refund_amount DECIMAL(19,2) NOT NULL COMMENT '총 환불 금액',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '환불 요청 시각',
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '환불 정보 수정 시각',
    FOREIGN KEY (purchase_id) REFERENCES purchase(id)
) COMMENT '환불 정보'; 