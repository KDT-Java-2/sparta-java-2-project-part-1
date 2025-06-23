CREATE TABLE user --사용자
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(50)  NOT NULL,
    birth           VARCHAR(8)   NOT NULL,
    email           VARCHAR(255) NOT NULL UNIQUE,
    password_hash   VARCHAR(255) NOT NULL,
    phone           VARCHAR(13)  NOT NULL,
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

);

CREATE TABLE category -- 카테고리
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parent_id BIGINT DEFAULT NULL COMMENT '부모 카테고리 ID (자기 참조)',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE product --물건
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(50)  NOT NULL,
    description   VARCHAR(200),
    price         DECIMAL(15,2) NOT NULL ,
    stock         INT NOT NULL DEFAULT 0,
    category_id   BIGINT NOT NULL ,
    created_at    DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE purchase -- 주문
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id         BIGINT NOT NULL ,
    order_no        VARCHAR(16) NOT NULL, -- 주문 번호
    total_price     DECIMAL(15,2) NOT NULL , -- 전체 금액
    status          VARCHAR(20) DEFAULT 'PENDING' COMMENT 'PENDING,COMPLETED,CANCELED,CHANGED' , -- 주문 상태
    zip_code        VARCHAR(10), -- 우편번호
    address         VARCHAR(500), -- 기본주소
    address_detail  VARCHAR(250), -- 상세주소
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

);

CREATE TABLE purchase_product -- 주문 물건
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id     BIGINT NOT NULL ,
    product_id      BIGINT NOT NULL ,
    quantity        INT NOT NULL DEFAULT 0,
    price           DECIMAL(15,2) NOT NULL ,
    delivery_status VARCHAR(20) NOT NULL DEFAULT 'NONE' COMMENT 'READY,PICKED_UP,IN_TRANSIT,OUT_FOR_DELIVERY,DELIVERED,CANCELLED,RETURN_REQUESTED,RETURNED',
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

);

CREATE TABLE cart
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id         BIGINT NOT NULL ,
    product_id      BIGINT NOT NULL ,
    quantity        INT NOT NULL DEFAULT 0, --수량
    total_price     DECIMAL(15,2) NOT NULL , --총금액
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);