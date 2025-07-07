-- V14: 주문의 배송 정보를 관리하는 테이블
CREATE TABLE purchase_shipping (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '배송 정보 고유 식별자',
    purchase_id BIGINT NOT NULL UNIQUE COMMENT '배송 정보가 속한 주문의 ID (주문과 1:1 관계)',
    recipient_name VARCHAR(50) NOT NULL COMMENT '수령인 이름',
    address VARCHAR(255) NOT NULL COMMENT '배송 주소',
    zip_code VARCHAR(20) NOT NULL COMMENT '우편번호',
    phone_number VARCHAR(20) NOT NULL COMMENT '수령인 연락처',
    memo VARCHAR(500) COMMENT '배송 메모',
    status ENUM('PREPARING', 'SHIPPED', 'DELIVERED', 'CANCELED') NOT NULL DEFAULT 'PREPARING' COMMENT '배송 상태',
    tracking_number VARCHAR(100) COMMENT '운송장 번호',
    shipped_at DATETIME COMMENT '발송 처리 시각',
    delivered_at DATETIME COMMENT '배송 완료 시각',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시각',
    FOREIGN KEY (purchase_id) REFERENCES purchase(id)
) COMMENT '배송 정보'; 