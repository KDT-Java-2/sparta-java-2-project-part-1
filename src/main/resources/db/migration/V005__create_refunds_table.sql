CREATE TABLE refunds (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id BIGINT NOT NULL UNIQUE,
    reason VARCHAR(500) NOT NULL,
    refund_amount DECIMAL(12, 2) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'REQUESTED',
    admin_notes TEXT,
    processed_by BIGINT,
    requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    processed_at TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    -- 외래 키 제약 조건 (1:1 관계)
    CONSTRAINT fk_refunds_purchase FOREIGN KEY (purchase_id) REFERENCES purchases(id) ON DELETE CASCADE,
    
    -- 상태 값 제약 조건
    CONSTRAINT chk_refunds_status CHECK (status IN (
        'REQUESTED', 'UNDER_REVIEW', 'APPROVED', 'REJECTED', 
        'PROCESSING', 'COMPLETED', 'CANCELED'
    )),
    
    -- 환불 금액은 양수여야 함
    CONSTRAINT chk_refunds_amount CHECK (refund_amount > 0)
);

-- 상태별 환불 내역 조회를 위한 인덱스
CREATE INDEX idx_refunds_status ON refunds(status);

-- 환불 요청일별 조회를 위한 인덱스
CREATE INDEX idx_refunds_requested_at ON refunds(requested_at);

-- 환불 처리일별 조회를 위한 인덱스
CREATE INDEX idx_refunds_processed_at ON refunds(processed_at);

-- 처리자별 환불 내역 조회를 위한 인덱스
CREATE INDEX idx_refunds_processed_by ON refunds(processed_by); 