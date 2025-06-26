CREATE TABLE cart (
    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    alias VARCHAR(36) UNIQUE NOT NULL,

    -- user_id
    user_id BIGINT NOT NULL ,
    -- 수량
    quantity int NOT NULL,
    -- purchase_id
    purchase_id BIGINT ,

    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP
)