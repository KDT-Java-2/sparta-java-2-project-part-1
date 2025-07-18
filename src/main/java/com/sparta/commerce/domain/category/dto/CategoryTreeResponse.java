package com.sparta.commerce.domain.category.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter // children 리스트에 자식 노드를 추가하기 위해 Setter가 필요합니다.
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryTreeResponse {
  Long id;
  String name;
  Long parentId; // 부모 카테고리의 ID (트리 재조립에 사용)
  Integer depth;
  List<CategoryTreeResponse> children = new ArrayList<>(); // 자식 카테고리 리스트
}