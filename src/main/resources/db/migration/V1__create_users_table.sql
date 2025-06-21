CREATE TABLE user
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '회원 고유 번호',
    name          VARCHAR(50)  NOT NULL COMMENT '회원 이름',
    email         VARCHAR(255) NOT NULL UNIQUE COMMENT '회원 이메일',
    password_hash VARCHAR(255) NOT NULL COMMENT '회원 패스워드',
    created_at    DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT '가입날짜',
    updated_at    DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);

CREATE TABLE purchase
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '주문 고유 번호',
    user_id          BIGINT         NOT NULL COMMENT '회원 고유 번호',
    total_price      DECIMAL(10, 2) NOT NULL COMMENT '총가격',
    status           VARCHAR(20) DEFAULT 'PENDING' COMMENT 'PENDING, COMPLETED, CANCELED',
    shipping_address TEXT           NOT NULL COMMENT '배송 주소',
    created_at       DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at       DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);

CREATE TABLE purchase_item
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id BIGINT         NOT NULL COMMENT '주문 고유 번호',
    product_id  BIGINT         NOT NULL COMMENT '상품 고유 번호',
    quantity    INT            NOT NULL COMMENT '주문 수량',
    price       DECIMAL(10, 2) NOT NULL COMMENT '최종 상품 가격',
    created_at  DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at  DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);

CREATE TABLE cart
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT NOT NULL COMMENT '회원 고유 번호',
    product_id BIGINT NOT NULL COMMENT '상품 고유 번호',
    quantity   INT    NOT NULL COMMENT '수량',
    created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);

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

CREATE TABLE category
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    parent_id  BIGINT   DEFAULT NULL COMMENT '부모 카테고리 ID',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE TABLE refund
(
    purchase_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id    BIGINT       NOT NULL,
    user_id     BIGINT       NOT NULL,
    reason      TEXT         NOT NULL,
    status      VARCHAR(255) NOT NULL COMMENT 'pending, approved, canceled',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);