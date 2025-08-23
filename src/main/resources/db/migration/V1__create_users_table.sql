CREATE TABLE users
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,

    name             VARCHAR(50)  NOT NULL,
    email            VARCHAR(255) NOT NULL UNIQUE,
    password_hash    VARCHAR(255) NOT NULL,
    mobile_number    VARCHAR(20)  NOT NULL,

    sms_receive   TINYINT(1) NOT NULL DEFAULT 0,
    email_receive TINYINT(1) NOT NULL DEFAULT 0,

    grade            VARCHAR(20),
    status           VARCHAR(20),

    last_login_at    DATETIME,
    created_at       DATETIME    DEFAULT CURRENT_TIMESTAMP,
    updated_at       DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
