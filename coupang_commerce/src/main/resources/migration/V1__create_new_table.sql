CREATE TABLE user
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,

    name              VARCHAR(50)  NOT NULL,
    email             VARCHAR(255) NOT NULL UNIQUE,
    password_hash     VARCHAR(255) NOT NULL,

    phone_number      VARCHAR(20)  NOT NULL UNIQUE,
    profile_image_url VARCHAR(500),
    date_of_birth     DATE         NOT NULL,
    gender            VARCHAR(20)  NOT NULL,


    created_at        DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at        DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);

CREATE TABLE purchase
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,

    user_id           BIGINT NOT NULL COMMENT '구매한 사용자 ID',
    total_price       DECIMAL(10, 2) NOT NULL,
    status            VARCHAR(20) DEFAULT 'PENDING' COMMENT 'PENDING, COMPLETED, CANCELED',

    created_at        DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at        DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)

);

CREATE TABLE product
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    category_id BIGINT COMMENT '상품이 속한 카테고리 ID',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE purchase_item ( -- 단수형으로 이름 변경
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id BIGINT NOT NULL COMMENT '어떤 주문에 속하는지',
    product_id BIGINT NOT NULL COMMENT '어떤 상품인지',
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL COMMENT '주문 시점의 상품 가격',
    created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);

CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parent_id BIGINT DEFAULT NULL COMMENT '부모 카테고리 ID (자기 참조)',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE TABLE cart (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL DEFAULT 1
);

CREATE TABLE refunds (
   id           BIGSERIAL PRIMARY KEY,
   purchase_id  BIGINT      NOT NULL,
   reason       TEXT,
   status       VARCHAR(20) NOT NULL
);