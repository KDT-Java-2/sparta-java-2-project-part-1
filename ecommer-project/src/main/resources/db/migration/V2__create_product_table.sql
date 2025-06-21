CREATE TABLE product(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    -- uuid
    alias VARCHAR(36) UNIQUE NOT NULL,

    -- 상품이름
    name VARCHAR(200) NOT NULL,
    -- 상품 설명
    description TEXT NOT NULL,
    -- 상품 가격
    price DECIMAL NOT NULL ,
    -- 상품 재고
    stock int DEFAULT '0',
    -- 상품 등록자
    user_id BIGINT,
    -- 카테고리
    category_id BIGINT,

    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP
)