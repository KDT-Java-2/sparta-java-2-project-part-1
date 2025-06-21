CREATE TABLE purchase (
    id BIGINT AUTO_INCREMENT PRIMARY KEY comment '주문 고유 ID',
    user_id BIGINT NOT NULL comment '구매한 사용자 ID',
    total_price DECIMAL(10,2) NOT NULL comment '총 결제 금액',
    status varchar(20) default 'PENDING' comment '주문 상태',
    shipping_address TEXT NOT NULL comment '배송 주소',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);