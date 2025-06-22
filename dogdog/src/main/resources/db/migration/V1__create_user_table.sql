CREATE TABLE member (
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    username            VARCHAR(50)  NOT NULL UNIQUE,
    password            VARCHAR(255) NOT NULL,
    name                VARCHAR(100) NOT NULL,
    email               VARCHAR(255) NOT NULL UNIQUE,
    phone_number        VARCHAR(20)  NOT NULL UNIQUE,
    role                VARCHAR(20)  NOT NULL DEFAULT 'MEMBER',
    status              VARCHAR(20)  NOT NULL DEFAULT 'ACTIVE',
    agreed_terms        BOOLEAN      NOT NULL DEFAULT FALSE,
    agreed_privacy      BOOLEAN      NOT NULL DEFAULT FALSE,
    agreed_marketing    BOOLEAN      NOT NULL DEFAULT FALSE,
    marketing_agreed_at DATETIME,
    created_at          DATETIME     DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at          DATETIME
);