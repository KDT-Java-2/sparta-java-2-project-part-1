-- V7: 상품 variant와 option 값을 연결하는 N:N 매핑 테이블
CREATE TABLE product_variant_option (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '연결 테이블 고유 식별자',
    product_variant_id BIGINT NOT NULL COMMENT '연결할 상품 variant의 ID',
    product_option_value_id BIGINT NOT NULL COMMENT '연결할 옵션 값의 ID',
    FOREIGN KEY (product_variant_id) REFERENCES product_variant(id) ON DELETE CASCADE,
    FOREIGN KEY (product_option_value_id) REFERENCES product_option_value(id) ON DELETE CASCADE,
    UNIQUE KEY uq_variant_option (product_variant_id, product_option_value_id)
) COMMENT '판매 상품(variant)과 옵션 선택지(value)를 연결하여 최종 조합'; 