package com.sparta.commerce.domain.category.dto.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {

  Long id;

  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  LocalDateTime createdAt;

}
