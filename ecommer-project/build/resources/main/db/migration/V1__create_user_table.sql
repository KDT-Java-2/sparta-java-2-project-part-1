CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    -- uuid
    alias VARCHAR(36) UNIQUE NOT NULL,

    -- 아이디
    username VARCHAR(200) UNIQUE NOT NULL ,
    -- 해싱된 패스워드
    password_hash VARCHAR(200) NOT NULL,
    -- 이메일
    email VARCHAR(200) UNIQUE  NOT NULL ,
    -- 이름
    name VARCHAR(50) NOT NULL,
    -- 생년월일
    birth DATETIME NOT NULL,
    -- 연락처
    phone_number VARCHAR(13) NOT NULL,
    -- 주소
    address VARCHAR(200) NOT NULL,
    -- 역할
    role VARCHAR(8) NOT NULL ,

    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP
)