-- V19: purchase 테이블에 쿠폰 할인 관련 정보를 추가
ALTER TABLE purchase
ADD COLUMN used_user_coupon_id BIGINT NULL COMMENT '주문에 사용한 user_coupon의 ID',
ADD COLUMN coupon_discount_amount DECIMAL(19, 2) DEFAULT 0.00 COMMENT '쿠폰으로 할인된 금액',
ADD CONSTRAINT fk_purchase_to_user_coupon FOREIGN KEY (used_user_coupon_id) REFERENCES user_coupon(id); 