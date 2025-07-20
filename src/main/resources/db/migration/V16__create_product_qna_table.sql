-- V16: 상품에 대한 문의(Q&A)를 관리하는 테이블
CREATE TABLE product_qna (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Q&A 고유 식별자',
    product_id BIGINT NOT NULL COMMENT '문의 대상 상품의 ID',
    user_id BIGINT NOT NULL COMMENT '질문 작성자의 ID',
    question TEXT NOT NULL COMMENT '질문 내용',
    answer TEXT COMMENT '답변 내용 (관리자 작성)',
    is_private BOOLEAN NOT NULL DEFAULT FALSE COMMENT '비밀글 여부',
    answered_at DATETIME COMMENT '답변 작성 시각',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '질문 생성 시각',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시각',
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
) COMMENT '상품 Q&A'; 