package org.example.prj_part_1.domain.product.repository;

import static org.example.prj_part_1.domain.product.entity.QProduct.product;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.prj_part_1.domain.product.dto.ProductSearchRequest;
import org.example.prj_part_1.domain.product.dto.ProductSearchResponse;
import org.example.prj_part_1.domain.product.dto.QProductSearchResponse;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

  private final JPAQueryFactory queryFactory;

  public ProductSearchResponse search(ProductSearchRequest request){

    List<ProductSearchResponse> products = queryFactory.query()
        .select(new QProductSearchResponse(
            product.id,
            product.name,
            product.price,
            product.stock
        ))
        .from(product)
        .where(
            //
        )
        .orderBy()
        .fetch();


    return null;
  }
}
