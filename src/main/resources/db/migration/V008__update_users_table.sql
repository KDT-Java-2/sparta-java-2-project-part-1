-- User 테이블에 password 필드 추가
ALTER TABLE users ADD COLUMN password VARCHAR(255) NOT NULL DEFAULT '';

-- name 컬럼을 username으로 변경
ALTER TABLE users CHANGE COLUMN name username VARCHAR(100) NOT NULL;

-- phone 컬럼을 nullable로 변경
ALTER TABLE users MODIFY COLUMN phone VARCHAR(20) NULL;

-- password 컬럼에 인덱스 추가는 보안상 불필요하므로 생략 