package me.chahyunho.projectweek1.domain.product.repository;

import static me.chahyunho.projectweek1.domain.category.entity.QCategory.category;
import static me.chahyunho.projectweek1.domain.product.entity.QProduct.product;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.chahyunho.projectweek1.domain.product.dto.CategoryProductDTO;
import me.chahyunho.projectweek1.domain.product.dto.QCategoryProductDTO;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class CategoryProductQueryRepository {

  private final JPAQueryFactory queryFactory;

  public List<CategoryProductDTO> findCategoryProducts(String categoryName) {
    return queryFactory.select(new QCategoryProductDTO(
            category.name.as("categoryName"),
            product.name.as("productName"),
            product.price,
            product.stock
        ))
        .from(category)
        .join(product).on(product.category.id.eq(category.id), categoryNameContains(categoryName))
        .fetch();
  }

  private BooleanExpression categoryNameContains(String categoryName) {
    return StringUtils.hasText(categoryName) ? category.name.contains(categoryName) : null;
  }

  public List<CategoryProductDTO> findCategoryProductsByCategoryId(Long categoryId) {
    return queryFactory.select(new QCategoryProductDTO(
            category.name.as("categoryName"),
            product.name.as("productName"),
            product.price,
            product.stock
        ))
        .from(category)
        .join(product).on(product.category.id.eq(category.id), categoryIdEq(categoryId))
        .fetch();
  }

  private BooleanExpression categoryIdEq(Long categoryId) {
    return categoryId != null ? category.id.eq(categoryId) : null;
  }
}
