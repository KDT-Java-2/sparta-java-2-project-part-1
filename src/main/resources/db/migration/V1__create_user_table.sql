CREATE TABLE user (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,

   name VARCHAR(50) -- 이미 users 테이블 안에 있으니 username보다 name이 깔끔
       NOT NULL,
   email VARCHAR(255)
       NOT NULL
       UNIQUE,
   password_hash VARCHAR(255)
       NOT NULL,

   created_at DATETIME
       DEFAULT CURRENT_TIMESTAMP,
   updated_at DATETIME
       NOT NULL
       DEFAULT CURRENT_TIMESTAMP
);