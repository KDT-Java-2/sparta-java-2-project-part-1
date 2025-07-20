-- V15: 구매한 상품에 대한 리뷰를 저장하는 테이블
CREATE TABLE purchase_product_review (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '리뷰 고유 식별자',
    product_id BIGINT NOT NULL COMMENT '리뷰 대상 상품의 ID (조회 편의성용)',
    user_id BIGINT NOT NULL COMMENT '리뷰 작성자의 ID',
    purchase_product_id BIGINT NOT NULL UNIQUE COMMENT '리뷰의 대상이 되는 원본 주문 상품 항목 ID (구매 인증용)',
    rating INT NOT NULL COMMENT '별점 (1~5)',
    title VARCHAR(100) NOT NULL COMMENT '리뷰 제목',
    content TEXT NOT NULL COMMENT '리뷰 내용',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시각',
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (purchase_product_id) REFERENCES purchase_product(id)
) COMMENT '구매 상품 리뷰'; 