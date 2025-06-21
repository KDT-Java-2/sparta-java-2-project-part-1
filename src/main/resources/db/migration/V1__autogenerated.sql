CREATE TABLE project_part1.category
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NOT NULL,
    name       VARCHAR(255)          NOT NULL,
    parent_id  BIGINT                NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE project_part1.product
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime              NOT NULL,
    updated_at    datetime              NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    `description` VARCHAR(255)          NULL,
    price         DECIMAL               NULL,
    stock         INT                   NULL,
    category_id   BIGINT                NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE project_part1.purchase
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    created_at  datetime              NOT NULL,
    updated_at  datetime              NOT NULL,
    user_id     BIGINT                NOT NULL,
    total_price DECIMAL               NOT NULL,
    status      VARCHAR(20)           NOT NULL,
    CONSTRAINT pk_purchase PRIMARY KEY (id)
);

CREATE TABLE project_part1.purchase_product
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    created_at  datetime              NOT NULL,
    updated_at  datetime              NOT NULL,
    purchase_id BIGINT                NULL,
    product_id  BIGINT                NULL,
    quantity    INT                   NULL,
    price       DECIMAL               NULL,
    CONSTRAINT pk_purchaseproduct PRIMARY KEY (id)
);

CREATE TABLE project_part1.user
(
    id                       BIGINT AUTO_INCREMENT NOT NULL,
    created_at               datetime              NOT NULL,
    updated_at               datetime              NOT NULL,
    name                     VARCHAR(50)           NOT NULL,
    email                    VARCHAR(255)          NOT NULL,
    password_hash            VARCHAR(255)          NOT NULL,
    gender                   CHAR                  NOT NULL,
    birth_day                datetime              NULL,
    phone_number             VARCHAR(11)           NOT NULL,
    postal_code              VARCHAR(5)            NULL,
    address                  VARCHAR(255)          NULL,
    profile_image_url        VARCHAR(255)          NULL,
    personal_info_consent_yn CHAR                  NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE project_part1.user
    ADD CONSTRAINT uc_user_email UNIQUE (email);

ALTER TABLE project_part1.category
    ADD CONSTRAINT FK_CATEGORY_ON_PARENT FOREIGN KEY (parent_id) REFERENCES project_part1.category (id);

ALTER TABLE project_part1.product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES project_part1.category (id);

ALTER TABLE project_part1.purchase_product
    ADD CONSTRAINT FK_PURCHASEPRODUCT_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES project_part1.product (id);

ALTER TABLE project_part1.purchase_product
    ADD CONSTRAINT FK_PURCHASEPRODUCT_ON_PURCHASE FOREIGN KEY (purchase_id) REFERENCES project_part1.purchase (id);

ALTER TABLE project_part1.purchase
    ADD CONSTRAINT FK_PURCHASE_ON_USER FOREIGN KEY (user_id) REFERENCES project_part1.user (id);