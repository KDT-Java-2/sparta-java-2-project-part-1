-- V21: 결제 트랜잭션 내역을 저장하는 테이블
CREATE TABLE payment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '결제 고유 식별자',
    purchase_id BIGINT NOT NULL COMMENT '주문 ID',
    amount INT NOT NULL COMMENT '결제 금액',
    method VARCHAR(20) NOT NULL COMMENT '결제 수단 (CARD, BANK, POINT 등)',
    status VARCHAR(20) NOT NULL COMMENT '결제 상태 (SUCCESS, FAIL, CANCEL 등)',
    transaction_id VARCHAR(100) COMMENT 'PG사 거래번호',
    paid_at DATETIME COMMENT '결제 완료 시각',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시각',
    FOREIGN KEY (purchase_id) REFERENCES purchase(id) ON DELETE CASCADE
) COMMENT '결제 트랜잭션 내역'; 