CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id varchar(50) NOT NULL,
    password varchar(255) NOT NULL,
    name varchar(50) NOT NULL,
    email varchar(255),
    phone_number varchar(50),
    role varchar(20) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);