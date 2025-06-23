CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 고유 ID',

    email VARCHAR(255) NOT NULL UNIQUE COMMENT '사용자 이메일 (로그인 ID)',
    password_hash VARCHAR(255) NOT NULL COMMENT '비밀번호 해시 값',
    role VARCHAR(20) NOT NULL DEFAULT 'USER' COMMENT '사용자 역할 (예: USER, ADMIN)',

    active_yn CHAR(1) NOT NULL DEFAULT 'Y' COMMENT '로그인 가능 여부 (Y: 가능, N: 불가능)',
    deleted_yn CHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제 여부 (Y: 삭제됨, N: 정상)',
    deleted_at DATETIME DEFAULT NULL COMMENT '삭제 처리된 시각',

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '계정 생성 시각',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '계정 정보 마지막 수정 시각'

) COMMENT='사용자 기본 정보 테이블';

CREATE TABLE user_agreements (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '약관 동의 테이블 기본 키',
    user_id BIGINT NOT NULL COMMENT 'users 테이블의 사용자 ID',

    terms_agreed_yn CHAR(1) NOT NULL DEFAULT 'N' COMMENT '서비스 이용 약관 동의 여부 (Y/N)',
    terms_agreed_at DATETIME DEFAULT NULL COMMENT '서비스 이용 약관 동의 일시',

    privacy_agreed_yn CHAR(1) NOT NULL DEFAULT 'N' COMMENT '개인정보 처리방침 동의 여부 (Y/N)',
    privacy_agreed_at DATETIME DEFAULT NULL COMMENT '개인정보 처리방침 동의 일시',

    marketing_agreed_yn CHAR(1) NOT NULL DEFAULT 'N' COMMENT '마케팅 수신 동의 여부 (Y/N)',
    marketing_agreed_at DATETIME DEFAULT NULL COMMENT '마케팅 수신 동의 일시',

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '레코드 생성 시각',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '레코드 마지막 수정 시각'

) COMMENT='사용자 약관 동의 테이블';

CREATE TABLE user_profiles (
   id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 프로필 고유 ID',
   user_id BIGINT NOT NULL COMMENT 'users 테이블의 사용자 ID (논리적 참조)',

   username VARCHAR(50) COMMENT '사용자 표시 이름 또는 닉네임',
   phone_number VARCHAR(20) COMMENT '사용자 전화번호',
   birthdate DATE COMMENT '생년월일',
   gender VARCHAR(10) COMMENT '성별 (예: MALE, FEMALE)',

   created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '프로필 생성 시각',
   updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '프로필 정보 마지막 수정 시각'
) COMMENT='사용자 프로필 정보 테이블';

CREATE TABLE user_balance (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '잔액 고유 ID',
    user_id BIGINT NOT NULL COMMENT '사용자 ID',
    current_balance DECIMAL(19,2) NOT NULL COMMENT '현재 잔액',
    last_updated DATETIME NOT NULL COMMENT '잔액 마지막 수정 시각',
    version INT DEFAULT 0 COMMENT '낙관적 락 버전 관리'
) COMMENT='사용자 적립금(잔액) 테이블';

CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '카테고리 고유 ID',

    name VARCHAR(255) NOT NULL COMMENT '카테고리 이름',
    code VARCHAR(100) DEFAULT NULL COMMENT '카테고리 식별 코드 (외부 연동/내부 설정용)',
    parent_id BIGINT DEFAULT NULL COMMENT '부모 카테고리 ID (자기 참조)',

    sort_order INT DEFAULT 0 COMMENT '정렬 우선순위 (같은 부모 내 오름차순 정렬)',

    visible_yn CHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용자에게 노출 여부 (Y/N)',
    deleted_yn CHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제 여부 (Y/N)',
    deleted_at DATETIME DEFAULT NULL COMMENT '삭제 처리 일시',

    description TEXT COMMENT '카테고리 설명 (운영자용)',

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시각'
) COMMENT='상품 분류용 계층형 카테고리 테이블';


CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '상품 고유 ID',
    name VARCHAR(255) NOT NULL COMMENT '상품 이름',
    description TEXT COMMENT '상품 설명',
    price DECIMAL(10, 2) NOT NULL COMMENT '판매가',
    category_id BIGINT COMMENT '카테고리 ID',

    visible_yn CHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용자 노출 여부 (Y/N)',
    deleted_yn CHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제 여부 (Y/N)',
    deleted_at DATETIME DEFAULT NULL COMMENT '삭제 일시',

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시각'
) COMMENT='상품 기본 정보 테이블';

CREATE TABLE product_inventory (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '재고 고유 ID',
    product_id BIGINT NOT NULL COMMENT '상품 ID (논리적 참조)',

    sock INT NOT NULL DEFAULT 0 COMMENT '현재 재고 수량',

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '레코드 생성 시각',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '재고 수정 시각'
) COMMENT='상품 재고 관리 테이블 (확장 가능성 고려)';

CREATE TABLE purchase (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '주문 고유 ID',
    user_id BIGINT NOT NULL COMMENT '구매한 사용자 ID',

    total_price DECIMAL(10, 2) NOT NULL COMMENT '주문 당시 총 상품 금액 (할인 전)',
    shipping_address TEXT NOT NULL COMMENT '배송지 주소',

    status VARCHAR(20) DEFAULT 'PENDING' COMMENT '주문 상태 (PENDING: 결제 대기, COMPLETED: 결제 완료, CANCELED: 주문 취소)',

    deleted_yn CHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제 여부 (Y/N)',
    deleted_at DATETIME DEFAULT NULL COMMENT '삭제 시각',

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '주문 생성 시각',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '주문 정보 마지막 수정 시각'
) COMMENT='사용자 주문 정보 테이블';

CREATE TABLE purchase_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '주문 항목 고유 ID',

    purchase_id BIGINT NOT NULL COMMENT '소속된 주문 ID',
    product_id BIGINT NOT NULL COMMENT '주문한 상품 ID',

    quantity INT NOT NULL COMMENT '주문 수량',
    price DECIMAL(10, 2) NOT NULL COMMENT '주문 시점의 상품 단가',

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '주문 항목 생성 시각',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '주문 항목 수정 시각'
) COMMENT='주문에 포함된 상품 항목 테이블';

CREATE TABLE cart (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '장바구니 고유 ID',
    user_id BIGINT NOT NULL COMMENT '사용자 ID',

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '장바구니 생성 시각',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '장바구니 수정 시각'
) COMMENT='사용자별 장바구니 테이블';

CREATE TABLE cart_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '장바구니 항목 고유 ID',
    cart_id BIGINT NOT NULL COMMENT '소속된 장바구니 ID',
    product_id BIGINT NOT NULL COMMENT '담은 상품 ID',
    quantity INT NOT NULL DEFAULT 1 COMMENT '담은 수량',

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '장바구니 항목 생성 시각',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '장바구니 항목 수정 시각'
) COMMENT='장바구니에 담긴 상품 항목 테이블';

CREATE TABLE refund (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '환불 고유 ID',
    payment_id BIGINT NOT NULL COMMENT '환불이 발생한 결제 ID',

    total_refund_amount DECIMAL(10,2) NOT NULL COMMENT '총 환불 요청 금액',
    reason VARCHAR(255) COMMENT '환불 요청 사유',
    status VARCHAR(20) NOT NULL DEFAULT 'REQUESTED' COMMENT '환불 상태',

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '환불 요청 시각',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '환불 정보 수정 시각'
) COMMENT='환불 요청에 대한 대표 정보';

CREATE TABLE refund_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '환불 항목 고유 ID',
    refund_id BIGINT NOT NULL COMMENT '소속된 환불 요청 ID',
    purchase_item_id BIGINT NOT NULL COMMENT '환불 대상 주문 항목 ID',

    refund_quantity INT NOT NULL COMMENT '환불 수량',
    refund_amount DECIMAL(10, 2) NOT NULL COMMENT '해당 항목 환불 금액',

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '환불 항목 생성 시각'
) COMMENT='상품 단위 환불 상세 이력';

CREATE TABLE coupon (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '쿠폰 정책 고유 ID',
    name VARCHAR(255) NOT NULL COMMENT '쿠폰 이름',
    discount_type VARCHAR(50) NOT NULL COMMENT '할인 유형 (예: PERCENT, FIXED)',
    discount_value DECIMAL(10,2) NOT NULL COMMENT '할인 값',
    minimum_order_amount DECIMAL(10,2) NOT NULL COMMENT '최소 주문 금액',
    limit_count INT NOT NULL COMMENT '발급 가능한 최대 수량',
    issued_count INT NOT NULL COMMENT '현재까지 발급된 수량',
    valid_from DATETIME NOT NULL COMMENT '유효 시작일',
    valid_until DATETIME NOT NULL COMMENT '유효 종료일'
) COMMENT='쿠폰 정책 테이블';

CREATE TABLE issued_coupon (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '발급된 쿠폰 고유 ID',
    user_id BIGINT NOT NULL COMMENT '쿠폰을 받은 사용자 ID',
    coupon_id BIGINT NOT NULL COMMENT '어떤 쿠폰 정책에 따른 쿠폰인지',
    used BOOLEAN NOT NULL DEFAULT FALSE COMMENT '사용 여부',
    used_at DATETIME DEFAULT NULL COMMENT '사용된 시점',
    created_at DATETIME NOT NULL COMMENT '발급 시각'
) COMMENT='사용자에게 발급된 쿠폰 테이블';

CREATE TABLE payment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '결제 고유 ID',
    order_id BIGINT NOT NULL COMMENT '구매 ID (purchase 테이블 참조)',

    amount DECIMAL(10, 2) NOT NULL COMMENT '결제 금액',
    issued_coupon_id BIGINT DEFAULT NULL COMMENT '사용된 쿠폰 ID',
    status VARCHAR(20) NOT NULL COMMENT '결제 상태',

    created_at DATETIME NOT NULL COMMENT '결제 생성 시각'
) COMMENT='결제 내역 테이블';












