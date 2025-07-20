-- V10: 주문의 기본 정보를 저장하는 테이블 (영수증 헤더)
CREATE TABLE purchase (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '주문 고유 식별자 (주문번호)',
    user_id BIGINT NOT NULL COMMENT '주문한 사용자의 ID',
    total_price DECIMAL(19, 2) DEFAULT NULL COMMENT '주문의 총 결제 금액',
    status VARCHAR(20) NOT NULL COMMENT '주문 상태 (예: PENDING, COMPLETED, CANCELED)',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '주문 생성 시각',
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '주문 정보 수정 시각',
    FOREIGN KEY (user_id) REFERENCES user(id)
) COMMENT '주문 정보'; 