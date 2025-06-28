-- V18: 사용자에게 발급된 개별 쿠폰 인스턴스를 관리하는 테이블
CREATE TABLE user_coupon (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 쿠폰 고유 식별자',
    user_id BIGINT NOT NULL COMMENT '쿠폰을 소유한 사용자의 ID',
    coupon_id BIGINT NOT NULL COMMENT '발급된 쿠폰의 종류 ID',
    is_used BOOLEAN NOT NULL DEFAULT FALSE COMMENT '쿠폰 사용 여부',
    used_at DATETIME COMMENT '쿠폰 사용 시각',
    expires_at DATETIME NOT NULL COMMENT '사용자별 쿠폰 만료 시각',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '쿠폰 발급 시각',
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (coupon_id) REFERENCES coupon(id)
) COMMENT '사용자 보유 쿠폰'; 