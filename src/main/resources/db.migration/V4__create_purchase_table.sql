CREATE TABLE purchase (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id BIGINT NOT NULL,
                          total_price DECIMAL(15, 2),
                          status VARCHAR(20) NOT NULL,
                          created_at DATETIME NOT NULL,
                          updated_at DATETIME,

                          CONSTRAINT fk_purchase_user FOREIGN KEY (user_id) REFERENCES user(id)
);
