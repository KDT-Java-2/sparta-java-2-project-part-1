alter table refund
    modify column created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '등록일';
alter table refund
    modify column updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
