package com.sparta.project1.domain.product.dto.admin;

import com.sparta.project1.domain.category.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductAdminRegisterRequest {

    //@NotNull은 빈문자열은 체크안하므로 입력안한것까지 체크는 NotBlank 쓰는데 문자열만 허용하니 다른자료형은 NotNull 쓰자

    @NotBlank(message = "상품명은 필수입니다.")
    String name;

    @NotBlank(message = "설명문은 필수입니다.")
    String description;

    @Positive
    @NotNull
    BigDecimal price;

    @Positive
    @NotNull
    Integer stock;

    @NotNull(message = "카테고리 ID는 필수입니다.")
    Long categoryId;

}
