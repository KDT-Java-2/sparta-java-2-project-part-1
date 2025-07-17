-- cart table (장바구니 테이블)
CREATE TABLE `cart`
(
    `id`         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '장바구니 ID (PK)',
    `user_id`    BIGINT NOT NULL COMMENT '사용자 ID (FK)',
    `product_id` BIGINT NOT NULL COMMENT '상품 ID (FK)',
    `quantity`   INT      DEFAULT 1 COMMENT '상품 수량',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자'
) COMMENT = '장바구니';

-- refund table (환불 테이블)
CREATE TABLE `refund`
(
    `id`          BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '환불 ID (PK)',
    `purchase_id` BIGINT NOT NULL COMMENT '주문 ID (FK)',
    `reason`      TEXT   NOT NULL COMMENT '환불 사유',
    `status`      VARCHAR(20) DEFAULT 'PENDING' COMMENT '환불 상태 (PENDING, REJECTED, COMPLETED)',
    `created_at`  DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
    `updated_at`  DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자'
) COMMENT = '환불';