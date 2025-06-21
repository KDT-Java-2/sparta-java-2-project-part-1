alter table user
    add column user_status VARCHAR(10) NOT NULL
        COMMENT '사용자 상태 : ACTIVE, INACTIVE, BLOCKED' after password_hash;