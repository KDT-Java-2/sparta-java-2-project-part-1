-- 사용자 테이블
CREATE TABLE user (
                      id            BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name          VARCHAR(50)  NOT NULL,
                      email         VARCHAR(255) NOT NULL UNIQUE,  -- 이메일 중복 방지
                      phone         VARCHAR(20)  NOT NULL,
                      password_hash VARCHAR(255) NOT NULL,
                      created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updated_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 카테고리 테이블 (계층 구조 + 사용 여부 + 정렬 순서)
CREATE TABLE category (
                          id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name        VARCHAR(255) NOT NULL,
                          parent_id   BIGINT DEFAULT NULL,  -- 상위 카테고리 ID (자기참조)
                          sort_order  INT DEFAULT 0,        -- UI 정렬 우선순위
                          is_active   BOOLEAN NOT NULL DEFAULT TRUE,  -- 노출 여부
                          remark      VARCHAR(255),
                          created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          CONSTRAINT fk_category_parent FOREIGN KEY (parent_id) REFERENCES category(id)
);

-- 상품 테이블
CREATE TABLE product (
                         id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                         category_id BIGINT         NOT NULL,
                         name        VARCHAR(255)   NOT NULL,
                         description VARCHAR(255),
                         price       DECIMAL(10, 2) NOT NULL,  -- 현재 판매 가격
                         stock       INT,                      -- 재고 수량
                         created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category(id)
);

-- 장바구니 테이블
CREATE TABLE cart (
                      id         BIGINT AUTO_INCREMENT PRIMARY KEY,
                      user_id    BIGINT   NOT NULL,
                      product_id BIGINT   NOT NULL,
                      quantity   INT,
                      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                      CONSTRAINT fk_cart_user FOREIGN KEY (user_id) REFERENCES user(id),
                      CONSTRAINT fk_cart_product FOREIGN KEY (product_id) REFERENCES product(id),
                      CONSTRAINT uq_cart_user_product UNIQUE (user_id, product_id)  -- 동일 상품 중복 담기 방지
);

-- 주문 테이블
CREATE TABLE purchase (
                          id             BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id        BIGINT         NOT NULL,
                          order_number   VARCHAR(50)    NOT NULL UNIQUE,  -- 주문번호 (조회/식별용)
                          total_price    DECIMAL(10, 2) NOT NULL,
                          payment_method VARCHAR(50),
                          status         VARCHAR(20)    NOT NULL DEFAULT 'PENDING',  -- 주문 상태 (PENDING, PAID 등)
                          created_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          CONSTRAINT fk_purchase_user FOREIGN KEY (user_id) REFERENCES user(id)
);

-- 주문 상세 테이블 (상품별 결제 기록 스냅샷)
CREATE TABLE purchase_product (
                                  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  purchase_id   BIGINT         NOT NULL,
                                  product_id    BIGINT         NOT NULL,
                                  product_name  VARCHAR(255)   NOT NULL,  -- 주문 당시 상품명 (변경 보존)
                                  unit_price    DECIMAL(10, 2) NOT NULL,  -- 주문 당시 단가
                                  quantity      INT            NOT NULL,
                                  total_price   DECIMAL(10, 2) NOT NULL,  -- 단가 * 수량
                                  created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  updated_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                  CONSTRAINT fk_purchase_product_purchase FOREIGN KEY (purchase_id) REFERENCES purchase(id),
                                  CONSTRAINT fk_purchase_product_product FOREIGN KEY (product_id) REFERENCES product(id)
);

-- 환불 테이블 (상품 단위로 1회만 환불 가능)
CREATE TABLE refund (
                        id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
                        purchase_id         BIGINT      NOT NULL,
                        purchase_product_id BIGINT      NOT NULL,
                        quantity            INT         NOT NULL,  -- 구매 수량과 동일해야 함
                        refund_amount       DECIMAL(10,2) NOT NULL,
                        reason              VARCHAR(255),
                        status              VARCHAR(20) NOT NULL DEFAULT 'PENDING',
                        created_at          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated_at          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        CONSTRAINT fk_refund_purchase FOREIGN KEY (purchase_id) REFERENCES purchase(id),
                        CONSTRAINT fk_refund_purchase_product FOREIGN KEY (purchase_product_id) REFERENCES purchase_product(id),
                        CONSTRAINT uq_refund_purchase_product UNIQUE (purchase_product_id)  -- 부분 환불 방지: 1회만 환불 가능
);
