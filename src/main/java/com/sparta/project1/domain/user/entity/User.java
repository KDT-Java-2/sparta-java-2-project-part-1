package com.sparta.project1.domain.user.entity;

import com.sparta.project1.domain.purchase.entity.Purchase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * DB 테이블을 자바코드에서 관리하기위한 데이터클래스,
 * 기본생성자 필수 DB 생성 시 테이블명 설정 테이블명을 다르게 하는경우 name 속성 설정필요하고 아니면 생략가능
 */
@Table
@Entity
@Getter
@Setter
//@Builder //모든 필드를 사용할경우 클래스에 명시하고 외부노출방지 일부만 노출시키고 싶은경우 생성자에 명시하여 활용
@DynamicInsert //값이 null이 아닌것만 INSERT 작성 (필수 권장)
@DynamicUpdate //기존과 비교하여 변경된 내용만 UPDATE 작성 (필수 권장)
@FieldDefaults(level = AccessLevel.PRIVATE)
//모든 필드를 일괄 지원되어 필드에 일일이 명시안해도됨(단, 엔티티, DTO에서만 사용하고 서비스, 컨트롤러에서는 권장x)
@NoArgsConstructor
public class User {

    @Id //PK 부여
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MYSQL auto increment 역할 수행
    Long id;

    // @Column: JPA가 테이블 생성시 컬럼정보 매핑
    // username : 컬럼명과 필드명 동일 시 생략 가능
    // nullable: 디폴트 TRUE라서 false는 별도 설정
    // length: 기본 255라서 255아닌경우 별도 설정
    @Column(nullable = false, length = 50)
    String username; //필드명은 카멜방식 권장, DB 필드명으로 생성

    @Column
    String email;

    @Column
    String password;

    // (name = "created_at") 같이 언더바만 차이있으면 JPA가 알아서 카멜케이스변환해줌
    // updatable = false 넣어 create만 되게 방지
    @Column(nullable = false, updatable = false)
    @CreationTimestamp //CURRENT TIMESTAMP 속성 지원
            LocalDateTime createdAt; //자바 자주쓰는 날짜시간 함수

    @OneToMany(mappedBy = "user")
    private List<Purchase> purchases = new ArrayList<>();

    public void changeUserName(String username) {
        this.username = username;
    }

    @Builder //외부에 노출시킬 제한된 필드만 다루기위해 명시한 생성자에 명시하는걸 실무적 관점에서 권장
    public User(
            String username,
            String email,
            String password
    ) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}