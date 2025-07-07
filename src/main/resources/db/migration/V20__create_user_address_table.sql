-- V20: 유저별 배송지 정보를 저장하는 테이블
CREATE TABLE user_address (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '배송지 고유 식별자',
    user_id BIGINT NOT NULL COMMENT '유저 ID',
    recipient_name VARCHAR(50) NOT NULL COMMENT '수령인 이름',
    address VARCHAR(255) NOT NULL COMMENT '주소',
    zip_code VARCHAR(20) NOT NULL COMMENT '우편번호',
    phone_number VARCHAR(20) NOT NULL COMMENT '연락처',
    memo VARCHAR(500) DEFAULT NULL COMMENT '배송 메모',
    is_default BOOLEAN NOT NULL DEFAULT FALSE COMMENT '기본 배송지 여부',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시각',
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
) COMMENT '유저별 배송지 정보'; 