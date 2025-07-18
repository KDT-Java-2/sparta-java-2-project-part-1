CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) NOT NULL comment '카테고리 이름',
    parent_id BIGINT NOT NULL comment '부모 카테고리 ID',
    depth INT comment '뎁스 구분',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);