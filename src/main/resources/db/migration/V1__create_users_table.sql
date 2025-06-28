CREATE TABLE user
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    email         VARCHAR(255) NOT NULL UNIQUE,
    password      VARCHAR(255) NOT NULL,
    name          VARCHAR(50)  NOT NULL,
    nickname      VARCHAR(50)  NOT NULL,
    address       VARCHAR(255) NOT NULL,
    phone         VARCHAR(50)  NOT NULL,
    role          VARCHAR(50)  NOT NULL DEFAULT 'MEMBER',
    created_at    DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);