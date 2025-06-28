package me.chahyunho.projectweek1.domain.product.repository;


import static me.chahyunho.projectweek1.domain.category.entity.QCategory.category;
import static me.chahyunho.projectweek1.domain.product.entity.QProduct.product;
import static me.chahyunho.projectweek1.domain.purchase.entity.QPurchaseProduct.purchaseProduct;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.chahyunho.projectweek1.domain.product.dto.CategoryOrderCountDTO;
import me.chahyunho.projectweek1.domain.product.dto.ProductSearchResponse;
import me.chahyunho.projectweek1.domain.product.dto.QCategoryOrderCountDTO;
import me.chahyunho.projectweek1.domain.product.dto.QProductSearchResponse;
import me.chahyunho.projectweek1.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class ProductsQueryRepository {

  private final JPAQueryFactory queryFactory;

  public Page<ProductSearchResponse> findProducts(Long categoryId, BigDecimal minPrice,
      BigDecimal maxPrice,
      Pageable pageable, String sortBy) {
    List<ProductSearchResponse> products = queryFactory
        .select(new QProductSearchResponse(
            product.id,
            product.name,
            product.description,
            product.price,
            product.stock,
            product.createdAt
        ))
        .from(product)
        .join(category).on(product.category.eq(category))
        .where(
            categoryIdEq(categoryId),
            priceGoe(minPrice),
            priceLoe(maxPrice)
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .orderBy(getSortCondition(sortBy))
        .fetch();

    Long totalCount = queryFactory
        .select(product.count())
        .from(product)
        .join(category).on(product.category.eq(category))
        .where(
            categoryIdEq(categoryId),
            priceGoe(minPrice),
            priceLoe(maxPrice)
        )
        .fetchOne();

    return new PageImpl<>(products, pageable, totalCount);
  }

  private OrderSpecifier<?> getSortCondition(String sortBy) {
    String field = "createdAt"; // 기본 필드
    String direction = "desc";  // 기본 방향

    if (sortBy != null && !sortBy.isBlank()) {
      String[] parts = sortBy.split(",");
      if (parts.length == 2) {
        field = parts[0].trim();
        direction = parts[1].trim().toLowerCase();
      }
    }

    PathBuilder<Product> product = new PathBuilder<>(Product.class, "product");

    return switch (field) {
      case "price" ->
          new OrderSpecifier<>(getOrder(direction), product.getNumber("price", BigDecimal.class));
      case "name" -> new OrderSpecifier<>(getOrder(direction), product.getString("name"));
      case "stock" -> new OrderSpecifier<>(getOrder(direction), product.getString("stock"));
      case "createdAt" -> new OrderSpecifier<>(getOrder(direction),
          product.getDateTime("createdAt", LocalDateTime.class));
      default ->
          new OrderSpecifier<>(Order.DESC, product.getDateTime("createdAt", LocalDateTime.class));
    };
  }

  private Order getOrder(String direction) {
    return "asc".equalsIgnoreCase(direction) ? Order.ASC : Order.DESC;
  }

  public List<ProductSearchResponse> searchProducts(String name, BigDecimal minPrice,
      BigDecimal maxPrice) {
    return queryFactory.select(new QProductSearchResponse(
            product.id,
            product.name,
            product.description,
            product.price,
            product.stock,
            product.createdAt
        ))
        .from(product)
        .where(
            nameContains(name),
            priceGoe(minPrice),
            priceLoe(maxPrice)
        ).fetch();
  }

  private BooleanExpression categoryIdEq(Long categoryId) {
    return categoryId != null ? product.category.id.eq(categoryId) : null;
  }

  private BooleanExpression nameContains(String name) {
    return StringUtils.hasText(name) ? product.name.contains(name) : null;
  }

  private BooleanExpression priceGoe(BigDecimal minPrice) {
    return minPrice != null ? product.price.goe(minPrice) : null;
  }

  private BooleanExpression priceLoe(BigDecimal maxPrice) {
    return maxPrice != null ? product.price.loe(maxPrice) : null;
  }


  public List<CategoryOrderCountDTO> findCategoryOrderCounts() {
    return queryFactory.select(new QCategoryOrderCountDTO(
            category.name.as("categoryName"),
            purchaseProduct.countDistinct().as("orderCount")
        ))
        .from(purchaseProduct)
        .join(product).on(purchaseProduct.product.eq(product))
        .join(category).on(purchaseProduct.product.category.eq(category))
        .groupBy(category.name)
        .fetch();
  }
}
