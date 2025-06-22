ALTER TABLE purchase
    DROP COLUMN shipping_address;

ALTER TABLE purchase
    ADD COLUMN order_number            VARCHAR(100) NOT NULL COMMENT '주문 번호',
    ADD COLUMN receiver_name           VARCHAR(100) NOT NULL COMMENT '받는 사람 이름',
    ADD COLUMN receiver_phone          VARCHAR(20)  NOT NULL COMMENT '받는 사람 연락처',
    ADD COLUMN receiver_address        VARCHAR(255) NOT NULL COMMENT '수령인 주소',
    ADD COLUMN receiver_address_detail VARCHAR(255) COMMENT '수령인 상세주소',
    ADD COLUMN receiver_zipcode        VARCHAR(20) COMMENT '수령인 우편번호';

