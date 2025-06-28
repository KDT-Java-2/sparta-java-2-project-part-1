# 🛒 Java E-commerce 시스템

Spring Boot 기반의 전자상거래 시스템 개발 프로젝트입니다.

## 📋 프로젝트 개요

- **프레임워크**: Spring Boot 3.5.3
- **언어**: Java 17
- **데이터베이스**: MySQL 8
- **빌드 도구**: Gradle
- **아키텍처**: Domain-Driven Design (DDD)

## 🚀 빠른 시작

### 필수 요구사항
- Java 17+
- MySQL 8.0+
- Gradle

### 실행 방법
```bash
# 1. 저장소 클론
git clone [repository-url]
cd java-02

# 2. 데이터베이스 설정
mysql -u root -p
CREATE DATABASE spring_db;

# 3. 애플리케이션 실행
./gradlew bootRun
```

## 📚 프로젝트 문서

| 문서 | 설명 |
|------|------|
| [📊 PROJECT_PROGRESS.md](./PROJECT_PROGRESS.md) | **주차별 개발 진행사항 및 계획** |
| [🚀 DEVELOPMENT_GUIDE.md](./DEVELOPMENT_GUIDE.md) | **개발 가이드라인 및 작업 관리 규칙** |
| [📖 README.md](./README.md) | **프로젝트 개요 및 시작 가이드** |

### 📊 주요 관리 문서

#### 1. PROJECT_PROGRESS.md
- **용도**: 주차별 개발 진행상황 추적
- **업데이트**: 매주 또는 주요 기능 완성 시
- **내용**: 완료된 작업, 다음 주차 계획, 기술적 의사결정

#### 2. DEVELOPMENT_GUIDE.md  
- **용도**: 개발 표준 및 작업 프로세스 정의
- **업데이트**: 개발 규칙 변경 시
- **내용**: 코딩 컨벤션, 디렉토리 구조, 작업 프로세스

## 🏗️ 프로젝트 구조

```
src/main/java/com/sparta/java_02/
├── domain/                     # 도메인별 패키지
│   ├── user/                   # 사용자 관리
│   ├── product/                # 상품 관리  
│   ├── category/               # 카테고리 관리
│   ├── cart/                   # 장바구니 기능
│   ├── purchase/               # 구매 처리
│   └── refund/                 # 환불 처리
└── Java02Application.java     # 메인 애플리케이션

src/main/resources/
├── application.yml             # 애플리케이션 설정
├── db/migration/               # Flyway 마이그레이션
└── static/                     # 정적 리소스
```

## 🎯 주요 기능

### ✅ 완료된 기능 (1주차)
- [x] 프로젝트 기본 설정
- [x] 데이터베이스 스키마 설계
- [x] 도메인 모델 구현 (User, Product, Category, Cart, Purchase, Refund)
- [x] Cart API 구현

### 🔄 진행 예정 (2주차)
- [ ] Product API 구현
- [ ] User 관리 기능
- [ ] Purchase/Refund API
- [ ] 데이터 유효성 검증
- [ ] 예외 처리 및 에러 핸들링

## 🛠️ 기술 스택

### Backend
- **Spring Boot 3.5.3**: 메인 프레임워크
- **Spring Data JPA**: ORM 및 데이터 접근
- **Flyway**: 데이터베이스 마이그레이션
- **Lombok**: 보일러플레이트 코드 감소

### Database
- **MySQL 8**: 메인 데이터베이스
- **HikariCP**: 커넥션 풀

### Build & Tools
- **Gradle**: 빌드 도구
- **Spring Boot DevTools**: 개발 편의성

## 📞 문제 해결

### 자주 발생하는 이슈
1. **포트 8080 충돌**: `lsof -ti:8080 | xargs kill -9`
2. **DB 연결 실패**: `application.yml`의 DB 설정 확인
3. **마이그레이션 오류**: 파일명 순서 및 형식 확인

### 도움말
- [Spring Boot 공식 문서](https://spring.io/projects/spring-boot)
- [Spring Data JPA 가이드](https://spring.io/projects/spring-data-jpa)
- [Flyway 마이그레이션 가이드](https://flywaydb.org/documentation/)

---

## 📝 제출 방법

#### **1. 제출 방법**

##### 1.1. **Branch 생성 및 작업**
제공된 주차별 Git repository에서 **신규 Branch**를 생성한 뒤 작업합니다.

- **Branch 이름 형식**  
  `"work/{고유번호}-{영문 이름}"`  
  **예시:** `work/1234-john-doe`

##### 1.2. **Commit 및 Push**
작업 내용을 **작업용 브랜치**에 Commit한 뒤 Push합니다.

##### 1.3. **PR 요청**
작업이 완료되면 **작업용 브랜치**에서 **제출용 브랜치**로 PR(Pull Request)을 생성합니다.

- **제출용 브랜치 이름 형식**  
  `"project/{고유번호}-{영문 이름}"`  
  **예시:** `project/1234-john-doe`

##### 1.4. **PR 병합**
프로젝트 이상이 없다면 **제출용 브랜치**에 PR을 병합합니다.

---

*마지막 업데이트: 2024년 1주차*
