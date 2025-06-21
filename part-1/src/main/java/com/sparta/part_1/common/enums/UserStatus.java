package com.sparta.part_1.common.enums;

/**
 * 유저의 휴면, 활동중, 기타 상태를 기록해놓기 위한 열거형 클래스 나중에 복귀 이벤트 어쩌구 할때 비즈니스 로직의 조건 값으로 활용을 생각했다.
 */
public enum UserStatus {
  ACTIVE, // 활동 중(디폴트)
  INACTIVE, // 휴면(부여 조건은 미구현)
  DELETED, // 탈퇴
  PENDING // 대기중(본인인증, 사용정보 제공 미동의, 필수정보 미입력등 특수사항)
}
