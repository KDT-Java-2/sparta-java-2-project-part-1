-- V17: 발급 가능한 프로모션 쿠폰의 종류를 정의하는 테이블
CREATE TABLE coupon (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '쿠폰 고유 식별자',
    name VARCHAR(255) NOT NULL COMMENT '쿠폰 이름 (예: 신규가입 10% 할인)',
    code VARCHAR(50) NOT NULL UNIQUE COMMENT '쿠폰 코드 (사용자가 입력하는 코드)',
    discount_type ENUM('PERCENT', 'FIXED_AMOUNT') NOT NULL COMMENT '할인 종류 (비율/정액)',
    discount_value DECIMAL(19, 2) NOT NULL COMMENT '할인 값 (예: 10.00, 5000)',
    min_purchase_amount DECIMAL(19, 2) DEFAULT 0.00 COMMENT '최소 주문 금액 조건',
    valid_from DATETIME NOT NULL COMMENT '쿠폰 유효 기간 시작일',
    valid_until DATETIME NOT NULL COMMENT '쿠폰 유효 기간 종료일',
    is_active BOOLEAN NOT NULL DEFAULT TRUE COMMENT '쿠폰 활성화 여부',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시각'
) COMMENT '프로모션 쿠폰'; 