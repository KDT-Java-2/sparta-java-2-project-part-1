# 🚀 개발 가이드라인 & 작업 관리

## 📁 프로젝트 구조 가이드

### 디렉토리 구조 규칙
```
src/main/java/com/sparta/java_02/
├── domain/                    # 도메인별 패키지
│   ├── {domain}/
│   │   ├── {Domain}.java      # 엔티티 클래스
│   │   ├── {Domain}Repository.java    # 데이터 접근 계층
│   │   ├── {Domain}Service.java       # 비즈니스 로직 계층
│   │   ├── {Domain}Controller.java    # 웹 계층 (선택적)
│   │   └── {Status/Type}.java         # 관련 enum 클래스
└── global/                    # 전역 설정 및 공통 기능
    ├── config/                # 설정 클래스
    ├── exception/             # 예외 처리
    └── util/                  # 유틸리티 클래스
```

### 네이밍 컨벤션
- **Entity**: PascalCase (예: `User`, `Product`)
- **Repository**: `{Entity}Repository`
- **Service**: `{Entity}Service`
- **Controller**: `{Entity}Controller`
- **Enum**: `{Entity}Status` 또는 `{Entity}Type`

---

## 🔄 작업 진행 프로세스

### 1. 새로운 기능 개발 시
1. **PROJECT_PROGRESS.md** 에서 해당 주차 계획 확인
2. 도메인별 디렉토리에서 작업 진행
3. 완료 후 **PROJECT_PROGRESS.md** 업데이트
4. 테스트 코드 작성 (`src/test/java/` 하위)

### 2. 파일 수정 시 체크리스트
- [ ] 수정 사유 명확화
- [ ] 관련 테스트 코드 업데이트
- [ ] API 변경 시 문서 업데이트
- [ ] 데이터베이스 스키마 변경 시 마이그레이션 파일 생성

### 3. 주차별 리뷰 포인트
- **구현 완료도**: 계획 대비 실제 완료 비율
- **코드 품질**: 설계 원칙 준수 여부
- **테스트 커버리지**: 단위 테스트 작성 현황
- **문서화**: API 문서 및 가이드 업데이트

---

## 📊 작업 추적 템플릿

### 새로운 도메인 추가 시
```markdown
### X.X {Domain} 도메인 (`domain/{domain}/`)
- **{Domain}.java**: 엔티티 설명
  - 기본 정보: 필드 목록
  - 관계 매핑: 다른 엔티티와의 관계
  - 비즈니스 메서드: 주요 메서드 설명
- **{Domain}Repository.java**: 데이터 접근 계층
- **{Domain}Service.java**: 비즈니스 로직 (선택적)
- **{Domain}Controller.java**: REST API (선택적)
```

### API 구현 시
```markdown
- **{Method} {URL}**: 기능 설명
  - 요청 파라미터: 설명
  - 응답 형태: 설명
  - 예외 처리: 가능한 에러 케이스
```

### 데이터베이스 변경 시
```markdown
- **V{XXX}__{description}.sql**: 마이그레이션 파일 설명
  - 변경 내용: 테이블/컬럼 변경사항
  - 영향 범위: 관련 엔티티 및 기능
```

---

## 🛠️ 개발 환경 설정

### 필수 도구
- **Java**: JDK 17
- **IDE**: IntelliJ IDEA 또는 VS Code
- **Database**: MySQL 8.0+
- **Build**: Gradle

### 로컬 실행 방법
```bash
# 1. MySQL 서버 시작
# 2. 데이터베이스 생성
CREATE DATABASE spring_db;

# 3. 애플리케이션 실행
./gradlew bootRun

# 4. 접속 확인
curl http://localhost:8080/actuator/health
```

### 테스트 실행
```bash
# 전체 테스트
./gradlew test

# 특정 도메인 테스트
./gradlew test --tests "*Cart*"
```

---

## 📋 주간 작업 체크리스트

### 매주 시작 시
- [ ] 지난 주 진행사항 리뷰
- [ ] 이번 주 목표 설정
- [ ] 필요한 리소스 및 의존성 확인

### 매주 종료 시
- [ ] **PROJECT_PROGRESS.md** 업데이트
- [ ] 완료된 기능 테스트 확인
- [ ] 다음 주 계획 수립
- [ ] 발생한 이슈 및 해결 방법 기록

### 매일 작업 시
- [ ] 작업 전 최신 코드 동기화
- [ ] 의미 있는 단위로 커밋
- [ ] 변경 사항이 있을 때마다 문서 업데이트

---

## 🚨 주의사항

### 코드 품질
1. **Single Responsibility**: 각 클래스는 하나의 책임만
2. **DRY**: 중복 코드 최소화
3. **Validation**: 입력값 검증 필수
4. **Exception Handling**: 적절한 예외 처리

### 데이터베이스
1. **Migration Only**: DDL은 반드시 마이그레이션 파일로
2. **Backup**: 중요한 변경 전 백업
3. **Rollback Plan**: 롤백 계획 수립

### API 설계
1. **RESTful**: REST 원칙 준수
2. **Consistent**: 일관된 응답 형태
3. **Documentation**: API 문서화 필수
4. **Versioning**: 버전 관리 고려

---

## 📞 문제 해결

### 자주 발생하는 이슈
1. **DB 연결 실패**: application.yml 설정 확인
2. **포트 충돌**: 8080 포트 사용 중인 프로세스 확인
3. **마이그레이션 오류**: V{번호} 순서 및 파일명 확인

### 도움이 필요할 때
1. **공식 문서**: Spring Boot, JPA 레퍼런스
2. **커뮤니티**: Stack Overflow, GitHub Issues
3. **팀 내 코드 리뷰**: 동료 개발자와 상의

---

*작성일: 2024년 1주차*
*관리자: 개발팀* 