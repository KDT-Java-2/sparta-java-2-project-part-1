CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    parent_id BIGINT NULL,
    display_order INT DEFAULT 0,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    -- 자기 참조 외래 키 (계층형 카테고리)
    CONSTRAINT fk_categories_parent FOREIGN KEY (parent_id) REFERENCES categories(id) ON DELETE CASCADE
);

-- 카테고리명 인덱스 (검색 성능 향상)
CREATE INDEX idx_categories_name ON categories(name);

-- 부모 카테고리 인덱스 (계층 조회)
CREATE INDEX idx_categories_parent_id ON categories(parent_id);

-- 활성 상태 인덱스 (활성 카테고리만 조회)
CREATE INDEX idx_categories_active ON categories(is_active);

-- 정렬 순서 인덱스
CREATE INDEX idx_categories_display_order ON categories(display_order); 