package com.js.commerce.domain.category.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@NoArgsConstructor // Jackson(혹은 MapStruct 같은 매핑 라이브러리)이 리플렉션으로 객체를 만들 때, 파라미터 없는 기본 생성자가 반드시 필요
@AllArgsConstructor // NoArgsConstructor와 Builder와 충돌하므로 AllArgsConstructor 명시
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryHierarchyDto {

  Long id;

  String name;

  @Builder.Default
  List<CategoryHierarchyDto> children = new ArrayList<>();

}
