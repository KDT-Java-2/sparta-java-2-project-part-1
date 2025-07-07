-- V8: 사용자의 장바구니 정보를 관리하는 테이블
CREATE TABLE cart (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '장바구니 고유 식별자',
    user_id BIGINT NOT NULL UNIQUE COMMENT '장바구니의 소유자 ID (사용자당 하나)',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
    FOREIGN KEY (user_id) REFERENCES user(id)
) COMMENT '사용자 장바구니'; 