CREATE TABLE user
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(50)  NOT NULL,
    email         VARCHAR(255) NOT NULL UNIQUE,
    password      VARCHAR(255) NOT NULL,
    created_at    DATETIME              DEFAULT CURRENT_TIMESTAMP,
    updated_at    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE category
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    parent_id  BIGINT   DEFAULT NULL COMMENT '부모 카테고리 ID (자기 참조)',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
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

CREATE TABLE purchase
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT         NOT NULL, -- FK: 어떤 user의 주문인지 식별, @JoinColumn(name = "user_id", nullable = false)
    total_price DECIMAL(10, 2) NOT NULL,
    status      VARCHAR(20)    NOT NULL,
    created_at  DATETIME                DEFAULT CURRENT_TIMESTAMP,
    updated_at  datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

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

CREATE TABLE cart
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT   NOT NULL,
    product_id BIGINT   NOT NULL,
    quantity   INT      NOT NULL,
    created_at DATETIME          DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE refund
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    purchase_id   BIGINT,
    refund_amount DECIMAL(10, 2) NOT NULL,
    status        VARCHAR(30)    NOT NULL,
    reason        VARCHAR(255),
    created_at    DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    DATETIME                DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);