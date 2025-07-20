CREATE TABLE category (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255),
                          parent_id BIGINT,
                          created_at DATETIME NOT NULL,
                          updated_at DATETIME,

                          CONSTRAINT fk_category_parent FOREIGN KEY (parent_id) REFERENCES category(id)
);
