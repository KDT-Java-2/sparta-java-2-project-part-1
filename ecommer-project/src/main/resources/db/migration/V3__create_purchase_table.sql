CREATE TABLE purchase(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    -- uuid
    alias VARCHAR(36) UNIQUE NOT NULL,

    -- 배송지 주소
    address TEXT NOT NULL,
    -- 주문자 번호
    phone_number VARCHAR(13) NOT NULL,
    -- 배송 요청 사항
    comment TEXT NOT NULL,
    -- 주문 총 금액
    total_price DECIMAL NOT NULL ,
    -- 주문상태(기본값 PENDDING)
    status VARCHAR(20) NOT NULL DEFAULT 'PEDDING',
    -- 주문자
    user_id BIGINT,
    -- 장바구니 내역
    cart_id BIGINT,

    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP
)