CREATE TABLE coupon (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    discount_amount DECIMAL(10,2) DEFAULT 0,
    discount_percent INT DEFAULT 0,
    valid_from DATETIME,
    valid_to DATETIME,
    min_purchase DECIMAL(10,2) DEFAULT 0,
    max_discount DECIMAL(10,2) DEFAULT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE coupon_usage (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    coupon_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    purchase_id BIGINT DEFAULT NULL, -- 구매 시에만 채워짐
    used_at DATETIME,
    UNIQUE (coupon_id, user_id, purchase_id)
);
