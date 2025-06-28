-- V1: 사용자 정보를 저장하는 테이블
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 고유 식별자 (내부용)',
    uuid BINARY(16) NOT NULL UNIQUE DEFAULT (UNHEX(REPLACE(UUID(), '-', ''))) COMMENT '사용자 외부 식별자 (UUID)',
    customer_number VARCHAR(20) UNIQUE DEFAULT NULL COMMENT '고객 번호 (운영용)',
    name VARCHAR(20) NOT NULL COMMENT '사용자 이름',
    email VARCHAR(50) NOT NULL UNIQUE COMMENT '이메일 주소 (로그인 ID)',
    password_hash VARCHAR(255) NOT NULL COMMENT '해시된 비밀번호',
    phone VARCHAR(20) NOT NULL COMMENT '전화번호',
    gender ENUM('MALE', 'FEMALE', 'OTHER') DEFAULT NULL COMMENT '성별',
    birth_date DATE DEFAULT NULL COMMENT '생년월일',
    profile_image_url VARCHAR(500) COMMENT '프로필 이미지 URL',
    login_type ENUM('EMAIL', 'GOOGLE', 'KAKAO', 'NAVER') DEFAULT 'EMAIL' COMMENT '로그인 유형',
    status ENUM('ACTIVE', 'INACTIVE', 'DELETED') DEFAULT 'ACTIVE' COMMENT '계정 상태',
    role ENUM('USER', 'PRODUCT_ADMIN', 'SUPER_ADMIN') DEFAULT 'USER' COMMENT '사용자 역할',
    last_login_at DATETIME DEFAULT NULL COMMENT '최종 로그인 시각',
    terms_agreed_at DATETIME NOT NULL COMMENT '서비스 이용 약관 동의 시각',
    privacy_agreed_at DATETIME NOT NULL COMMENT '개인정보 처리방침 동의 시각',
    marketing_agreed_at DATETIME DEFAULT NULL COMMENT '마케팅 정보 수신 동의 시각',
    is_email_verified BOOLEAN DEFAULT FALSE COMMENT '이메일 인증 여부',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시각'
) COMMENT '사용자 계정 정보'; 