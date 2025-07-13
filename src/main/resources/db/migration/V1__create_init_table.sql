-- user table (사용자 테이블)
CREATE TABLE `user`
(
    `id`           BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 ID (PK)',
    `name`         VARCHAR(50)  NOT NULL COMMENT '사용자 이름',
    `nickname`     VARCHAR(50)  NOT NULL UNIQUE COMMENT '사용자 닉네임',
    `email`        VARCHAR(255) NOT NULL UNIQUE COMMENT '사용자 이메일',
    `phone_number` VARCHAR(20) COMMENT '사용자 전화번호',
    `password`     VARCHAR(255) NOT NULL COMMENT '해시된 비밀번호',
    `status`       VARCHAR(20) DEFAULT 'ACTIVE' COMMENT '사용자 상태 (ACTIVE, INACTIVE, BANNED)',
    `role`         VARCHAR(20) DEFAULT 'CUSTOMER' COMMENT '사용자 역할 (CUSTOMER, SELLER, ADMIN)',
    `created_at`   DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT '가입일자',
    `updated_at`   DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자'
) COMMENT = '사용자';

-- category table (카테고리 테이블)
CREATE TABLE `category`
(
    `id`         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '카테고리 ID (PK)',
    `name`       VARCHAR(255) NOT NULL COMMENT '카테고리명',
    `parent_id`  BIGINT   DEFAULT NULL COMMENT '부모 카테고리 ID (FK)',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자'
) COMMENT = '카테고리';

-- brand table (브랜드 테이블)
CREATE TABLE `brand`
(
    `id`          BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '브랜드 ID (PK)',
    `seller_id`   BIGINT       NOT NULL COMMENT '판매자 ID (FK)',
    `name`        VARCHAR(255) NOT NULL UNIQUE COMMENT '브랜드명',
    `description` TEXT COMMENT '브랜드 소개',
    `logo_url`    VARCHAR(255) COMMENT '브랜드 로고 URL',
    `created_at`  DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
    `updated_at`  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자'
) COMMENT = '브랜드';

-- product table (상품 테이블)
CREATE TABLE `product`
(
    `id`            BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '상품 ID (PK)',
    `brand_id`      BIGINT         NOT NULL COMMENT '브랜드 ID (FK)',
    `category_id`   BIGINT         NOT NULL COMMENT '상품 카테고리 ID (FK)',
    `name`          VARCHAR(255)   NOT NULL COMMENT '상품명',
    `description`   TEXT COMMENT '상품 상세',
    `price`         DECIMAL(10, 2) NOT NULL COMMENT '상품 가격',
    `stock`         INT      DEFAULT 0 COMMENT '상품 재고',
    `thumbnail_url` VARCHAR(255) COMMENT '대표 이미지 URL',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
    `updated_at`    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자'
) COMMENT = '상품';

-- purchase table (주문 테이블)
CREATE TABLE `purchase`
(
    `id`               BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '주문 ID (PK)',
    `user_id`          BIGINT         NOT NULL COMMENT '주문자 ID (FK)',
    `total_price`      DECIMAL(10, 2) NOT NULL COMMENT '주문 가격',
    `status`           VARCHAR(20) DEFAULT 'PENDING' COMMENT '주문 상태 (PENDING, COMPLETED, CANCELED, REFUNDED)',
    `payment_method`   VARCHAR(50) DEFAULT 'CARD' COMMENT '결제 방식 (CARD, ACCOUNT_TRANSFER, KAKAO_PAY, etc.)',
    `shipping_address` TEXT           NOT NULL COMMENT '주문 배송지',
    `created_at`       DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
    `updated_at`       DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자'
) COMMENT = '주문';

-- purchase_product table (주문-상품 테이블)
CREATE TABLE `purchase_product`
(
    `id`              BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '주문-상품 ID (PK)',
    `purchase_id`     BIGINT         NOT NULL COMMENT '주문 ID (FK)',
    `product_id`      BIGINT         NOT NULL COMMENT '상품 ID (FK)',
    `quantity`        INT            NOT NULL COMMENT '상품 수량',
    `price`           DECIMAL(10, 2) NOT NULL COMMENT '주문 시점의 상품 가격',
    `delivery_status` VARCHAR(20) DEFAULT 'PENDING' COMMENT '배송 상태 (PENDING, SHIPPED, DELIVERED)',
    `created_at`      DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자'
) COMMENT = '주문 상품 상세';