# 🛒 Java E-commerce 프로젝트 진행사항

## 📋 프로젝트 개요
- **프로젝트명**: java-02 (Java Spring Boot 기반 E-commerce 시스템)
- **프레임워크**: Spring Boot 3.5.3
- **언어**: Java 17
- **데이터베이스**: MySQL 8
- **빌드 도구**: Gradle
- **브랜치**: work/3906-jungdae

---

## 📅 1주차 개발 진행사항 (Week 1 Progress)

### 🎯 완료된 작업 (Completed Tasks)

#### 1. 프로젝트 기본 설정 (Project Setup)
**디렉토리**: `/` (루트)
- **파일**: `build.gradle`, `settings.gradle`, `gradlew`, `gradlew.bat`
- **설정 내용**:
  - Spring Boot 3.5.3 설정
  - Flyway DB 마이그레이션 설정
  - MySQL 커넥터 의존성 추가
  - Lombok, JPA, Actuator 설정

#### 2. 애플리케이션 설정 (Application Configuration)
**디렉토리**: `src/main/resources/`
- **파일**: `application.yml`
- **설정 내용**:
  - MySQL 데이터베이스 연결 설정 (localhost:3306/spring_db)
  - JPA/Hibernate 설정 (ddl-auto: none)
  - Flyway 마이그레이션 설정
  - 로깅 설정 (Hibernate SQL 디버그)
  - 서버 포트 8080 설정

#### 3. 데이터베이스 스키마 설계 (Database Schema)
**디렉토리**: `src/main/resources/db/migration/`
- **V001__create_users_table.sql**: 사용자 테이블 생성
- **V002__create_products_table.sql**: 상품 테이블 생성
- **V003__create_cart_table.sql**: 장바구니 테이블 생성
- **V004__create_purchases_table.sql**: 구매 테이블 생성
- **V005__create_refunds_table.sql**: 환불 테이블 생성
- **V006__create_categories_table.sql**: 카테고리 테이블 생성
- **V007__add_category_to_products.sql**: 상품에 카테고리 외래키 추가

#### 4. 도메인 모델 개발 (Domain Models)
**디렉토리**: `src/main/java/com/sparta/java_02/domain/`

##### 4.1 User 도메인 (`domain/user/`)
- **User.java**: 사용자 엔티티
  - 기본 정보: id, email, name, phone
  - 관계 매핑: Cart, Purchase와 일대다 관계
  - 비즈니스 메서드: addCart(), removeCart(), addPurchase()
- **UserRepository.java**: 사용자 데이터 접근 계층

##### 4.2 Product 도메인 (`domain/product/`)
- **Product.java**: 상품 엔티티
  - 기본 정보: id, name, description, price, stockQuantity
  - 관계 매핑: Category와 다대일, Cart/Purchase와 일대다
  - 비즈니스 메서드: 재고 관리(decrease/increaseStock), 정보 업데이트
- **ProductRepository.java**: 상품 데이터 접근 계층

##### 4.3 Category 도메인 (`domain/category/`)
- **Category.java**: 카테고리 엔티티
  - 계층형 구조 (self-referencing): parent-children 관계
  - 기능: displayOrder, isActive, 경로 추적
  - 비즈니스 메서드: 활성화/비활성화, 계층 관리
- **CategoryRepository.java**: 카테고리 데이터 접근 계층

##### 4.4 Cart 도메인 (`domain/cart/`)
- **Cart.java**: 장바구니 엔티티
  - 사용자-상품 관계 매핑 (User, Product)
  - 수량 관리 및 검증 로직
- **CartController.java**: 장바구니 REST API
  - POST /api/cart: 상품 추가
  - GET /api/cart/{userId}: 장바구니 조회
  - PUT /api/cart: 수량 변경
  - DELETE /api/cart/{userId}/{productId}: 상품 삭제
- **CartService.java**: 장바구니 비즈니스 로직
- **CartRepository.java**: 장바구니 데이터 접근 계층

##### 4.5 Purchase 도메인 (`domain/purchase/`)
- **Purchase.java**: 구매 엔티티
  - 구매 정보: 수량, 단가, 총액, 결제방법, 배송주소
  - 상태 관리: PurchaseStatus enum 활용
  - 비즈니스 메서드: 상태 변경, 취소, 환불 가능 여부 확인
- **PurchaseStatus.java**: 구매 상태 enum
- **PurchaseService.java**: 구매 비즈니스 로직
- **PurchaseRepository.java**: 구매 데이터 접근 계층

##### 4.6 Refund 도메인 (`domain/refund/`)
- **Refund.java**: 환불 엔티티
  - Purchase와 일대일 관계
  - 환불 처리 상태 관리 (요청→승인→완료)
  - 관리자 처리 기능 (승인/거절/완료)
- **RefundStatus.java**: 환불 상태 enum
- **RefundService.java**: 환불 비즈니스 로직
- **RefundRepository.java**: 환불 데이터 접근 계층

#### 5. 메인 애플리케이션 클래스
**디렉토리**: `src/main/java/com/sparta/java_02/`
- **Java02Application.java**: Spring Boot 메인 클래스

#### 6. 웹 리소스 설정
**디렉토리**: `src/main/resources/static/`
- **index.html**: 기본 웹 페이지

### 🏗️ 아키텍처 특징
1. **도메인 주도 설계(DDD)**: 각 도메인별로 패키지 분리
2. **계층형 아키텍처**: Entity → Repository → Service → Controller
3. **RESTful API**: 표준 HTTP 메서드와 응답 코드 사용
4. **데이터베이스 마이그레이션**: Flyway를 통한 버전 관리
5. **JPA/Hibernate**: ORM을 통한 객체-관계 매핑
6. **Lombok**: 보일러플레이트 코드 감소

### 🔧 기술 스택
- **Backend**: Spring Boot 3.5.3, Spring Data JPA, Spring Security
- **Database**: MySQL 8, Flyway
- **Build Tool**: Gradle
- **기타**: Lombok, QueryDSL, Validation, Spring Boot Actuator, DevTools

### 📋 구현된 API 목록 (2주차)

#### 🔓 사용자 API (User-Facing)
| Method | Endpoint | 설명 | 상태 |
|--------|----------|------|------|
| POST | `/api/users` | 회원가입 | ✅ |
| GET | `/api/products` | 상품 목록 조회 (검색/페이징) | ✅ |
| GET | `/api/products/{id}` | 상품 상세 조회 | ✅ |
| GET | `/api/categories/hierarchy` | 카테고리 계층구조 조회 | ✅ |

#### 🔐 관리자 API (Admin-Facing)
| Method | Endpoint | 설명 | 상태 |
|--------|----------|------|------|
| POST | `/api/admin/products` | 상품 등록 | ✅ |
| PUT | `/api/admin/products/{id}` | 상품 수정 | ✅ |
| DELETE | `/api/admin/products/{id}` | 상품 삭제 | ✅ |
| POST | `/api/admin/categories` | 카테고리 등록 | ✅ |
| PUT | `/api/admin/categories/{id}` | 카테고리 수정 | ✅ |
| DELETE | `/api/admin/categories/{id}` | 카테고리 삭제 | ✅ |

---

## 📈 다음 주차 계획 (Next Week Plan)

### 🎯 2주차 목표 (Week 2 Goals)
- [x] 프로젝트 의존성 추가 (Spring Security, QueryDSL, Validation)
- [x] 도메인 모델 수정 (User password 필드, Product 제약조건)
- [x] 데이터베이스 마이그레이션 업데이트
- [x] 전역 설정 구현 (Security, QueryDSL, 예외처리)
- [x] 공통 응답 포맷 및 DTO 구조 설계
- [x] User API 구현 (회원가입)
- [x] Product API 구현 (CRUD - 사용자용/관리자용 분리)
- [x] Category API 구현 (계층구조 조회, 관리자 CRUD)
- [ ] 동적 상품 검색 기능 (QueryDSL)
- [x] 비즈니스 로직 검증 (카테고리 삭제 제약조건, 순환참조 방지)

### 📝 다음 진행 예정 작업
1. **동적 상품 검색 기능 (QueryDSL)**
   - 카테고리, 가격 범위, 키워드 검색
   - 복합 조건 필터링
   - 성능 최적화

2. **상품 삭제 제약조건 구현**
   - Purchase 테이블과 연동
   - 주문 완료된 상품 삭제 방지 로직

3. **API 테스트 및 검증**
   - 단위 테스트 작성
   - 통합 테스트 구현
   - API 문서화 (Swagger)

4. **추가 기능 및 최적화**
   - 로깅 및 모니터링
   - 성능 튜닝
   - 보안 강화

---

## 📋 작업 이력 (Work History)

### 2024-XX-XX (Week 1)
- ✅ 프로젝트 초기 설정 완료
- ✅ 데이터베이스 스키마 설계 및 마이그레이션 적용
- ✅ 도메인 모델 6개 완료 (User, Product, Category, Cart, Purchase, Refund)
- ✅ Cart API 구현 완료

### 2024-XX-XX (Week 2)
- ✅ 필수 의존성 추가 (Spring Security, QueryDSL, Validation)
- ✅ 도메인 모델 업데이트
  - User: password 필드 추가, username 필드 변경
  - Product: name 필드 unique 제약조건, price Integer 타입 변경
- ✅ 데이터베이스 마이그레이션 V008, V009 추가
- ✅ 전역 인프라 구현
  - Security 설정 (PasswordEncoder)
  - QueryDSL 설정 (JPAQueryFactory)
  - 전역 예외 처리 (GlobalExceptionHandler)
  - 공통 응답 포맷 (ApiResponse)
- ✅ User API 구현
  - POST /api/users (회원가입)
  - 이메일 중복 검증, 비밀번호 암호화
- ✅ Product API 구현
  - GET /api/products (상품 목록 조회 - 페이징, 정렬)
  - GET /api/products/{id} (상품 상세 조회)
  - POST /api/admin/products (상품 등록)
  - PUT /api/admin/products/{id} (상품 수정)
  - DELETE /api/admin/products/{id} (상품 삭제)
- ✅ Category API 구현
  - GET /api/categories/hierarchy (계층구조 조회)
  - POST /api/admin/categories (카테고리 등록)
  - PUT /api/admin/categories/{id} (카테고리 수정)
  - DELETE /api/admin/categories/{id} (카테고리 삭제)
- ✅ 비즈니스 로직 검증
  - 카테고리 순환참조 방지
  - 카테고리 삭제 제약조건 (하위 카테고리/상품 존재 시)

---

## 🏃‍♂️ 다음 업데이트 시점
- 매주 금요일 진행사항 업데이트
- 주요 기능 완성 시 즉시 업데이트
- 이슈 발생 시 해결 과정 기록

---

*마지막 업데이트: 2024년 X주차* 