CREATE TABLE users
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(50)  NOT NULL,
    email         VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at    DATETIME              DEFAULT CURRENT_TIMESTAMP,
    updated_at    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE purchase
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '주문 고유 번호',
    user_id          BIGINT         NOT NULL COMMENT '회원 고유 번호',
    total_price      DECIMAL(10, 2) NOT NULL COMMENT '총가격',
    status           ENUM('PENDING', 'COMPLETED', 'CANCELED') NOT NULL COMMENT '배송 상태',
    shipping_address TEXT           NOT NULL COMMENT '배송 주소',
    created_at       DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at       DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);

CREATE TABLE purchase_product
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id BIGINT         NOT NULL COMMENT '어떤 주문에 속하는지',
    product_id  BIGINT         NOT NULL COMMENT '어떤 상품인지',
    quantity    INT            NOT NULL,
    price       DECIMAL(10, 2) NOT NULL COMMENT '주문 시점의 상품 가격',
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
    category_id BIGINT         NOT NULL,
    name        VARCHAR(255)   NOT NULL,
    description TEXT,
    price       DECIMAL(10, 2) NOT NULL,
    stock       INT            NOT NULL,
    created_at  DATETIME                DEFAULT CURRENT_TIMESTAMP,
    updated_at  datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE category
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    parent_id  BIGINT   DEFAULT NULL COMMENT '부모 카테고리 ID (자기 참조)',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE TABLE refund
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id BIGINT NOT NULL,
    user_id     BIGINT NOT NULL,
    reason      TEXT   NOT NULL,
    status      ENUM('PENDING', 'APPROVED', 'CANCELED') NOT NULL COMMENT '환불 상태',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME  DEFAULT CURRENT_TIMESTAMP ON
UPDATE CURRENT_TIMESTAMP
);