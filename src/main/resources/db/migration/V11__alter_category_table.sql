alter table category
    add column description VARCHAR(255) NULL
        COMMENT '카테고리 설명' after category_nm;
