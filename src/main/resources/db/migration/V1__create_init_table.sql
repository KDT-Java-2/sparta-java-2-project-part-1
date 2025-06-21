CREATE TABLE user
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(50)  NOT NULL,
    email         VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at    DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- purchase 테이블
CREATE TABLE purchase
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT         NOT NULL, -- FK: 어떤 user의 주문인지 식별
    total_price DECIMAL(10, 2) NOT NULL,
    status      VARCHAR(20)    NOT NULL,
    updated_at  DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_at  DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)
);


-- product Table
CREATE TABLE product
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    description TEXT,
    price       DECIMAL(10, 2) NOT NULL,
    stock       INT            NOT NULL DEFAULT 0,
    category_id BIGINT COMMENT '상품이 속한 카테고리 ID',
    created_at  DATETIME                DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


-- category Table
CREATE TABLE category
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    parent_id  BIGINT   DEFAULT NULL COMMENT '부모 카테고리 ID (자기 참조)',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- purchase_items Table
CREATE TABLE purchase_product
( -- 단수형으로 이름 변경
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id BIGINT         NOT NULL COMMENT '어떤 주문에 속하는지',
    product_id  BIGINT         NOT NULL COMMENT '어떤 상품인지',
    quantity    INT            NOT NULL,
    price       DECIMAL(10, 2) NOT NULL COMMENT '주문 시점의 상품 가격',
    created_at  DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at  DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);

# **설계 요구사항**:
# 1. **테이블 설계 (Flyway)**: 아래 구조를 참고하여 `cart` 테이블 생성 SQL을 Flyway 마이그레이션 파일로 작성하세요.
# - `cart` 테이블: `id` (PK), `user_id` (FK to `user`), `product_id` (FK to `product`), `quantity`
# 2. **엔티티 설계 (JPA)**: `Cart` 엔티티 클래스를 작성하고, `User`, `Product` 엔티티와의 연관관계를 어노테이션으로 매핑하세요.
CREATE TABLE cart
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity   INT    NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


# **설계 요구사항**:
# 1. **상태 관리 설계**: `purchase` 테이블의 `status` 컬럼에 저장될 값들('PENDING', 'COMPLETED', 'CANCELED' 등)을 관리하기 위한 **Java Enum(`PurchaseStatus`)**을 설계하세요. `refunds` 테이블도 동일하게 `RefundStatus` Enum을 설계합니다.
# 2. **테이블 설계 (Flyway)**: `refunds` 테이블이 없다면 생성합니다. `purchase` 테이블과 1:1 관계를 맺기 위한 `purchase_id` 컬럼이 포함되어야 합니다.
# - `refunds` 테이블: `id` (PK), `purchase_id` (FK to `purchase`), `reason`, `status`
# 3. **엔티티 설계 (JPA)**: `Refund` 엔티티를 작성하고, `Purchase` 엔티티와의 **`@OneToOne`** 연관관계를 매핑하세요.
CREATE TABLE refunds
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id   BIGINT       NOT NULL COMMENT '어떤 주문에 속하는지',
    reason        VARCHAR(255) NOT NULL COMMENT '환불 사유',
    status        VARCHAR(20)  NOT NULL COMMENT '환불 상태 (REQUESTED(요청), APPROVED(승인), REJECTED(거절), COMPLETED(완료) 등)',
    refund_amount DECIMAL(10, 2) DEFAULT 0 COMMENT '환불 금액',
    method        VARCHAR(50) COMMENT '환불 수단 (카드, 계좌이체, 포인트 등)',
    bank_account  VARCHAR(100) COMMENT '환불 계좌 정보 (선택)',
    is_partial    BOOLEAN        DEFAULT FALSE COMMENT '부분 환불 여부',
    refunded_by   VARCHAR(100) COMMENT '환불 처리자',
    refunded_at   DATETIME COMMENT '실제 환불 완료 시간',
    note          TEXT COMMENT '관리자 코멘트나 메모',
    created_at    DATETIME       DEFAULT CURRENT_TIMESTAMP,
    updated_at    DATETIME       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)




