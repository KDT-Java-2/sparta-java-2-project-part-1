CREATE TABLE user (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 ID',
    user_nm         VARCHAR(50) NOT NULL COMMENT '사용자명',
    email           VARCHAR(255) NOT NULL UNIQUE COMMENT '사용자 이메일',
    password_hash   VARCHAR(255) NOT NULL COMMENT '패스워드 해시',
    last_login_at   DATETIME DEFAULT NULL COMMENT '마지막 로그인 시간',
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
    updated_at      DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);