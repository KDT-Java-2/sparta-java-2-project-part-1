package com.js.commerce.domain.product.mapper;

import com.js.commerce.domain.product.dto.PageInfo;
import com.js.commerce.domain.product.dto.ProductSearchPagedResponse;
import com.js.commerce.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@Mapper(
    componentModel = "spring",
    uses = ProductMapper.class
)
public abstract class ProductPagedMapper {

//  @Mapping(source = ".", target = "pageable",
//      qualifiedByName = "mapPageable")
//  public abstract ProductSearchPagedResponse toPagedResponse(Page<Product> page);
//
//  @Named("mapPageable")
//  protected PageInfo mapPageable(Page<Product> page) {
//    return new PageInfo(page.getNumber(), page.getSize());
//  }

  @Autowired
  protected ProductMapper productMapper;

  public ProductSearchPagedResponse toPagedResponse(Page<Product> page) {
    return ProductSearchPagedResponse.builder()
        .content(productMapper.toResponseList(page.getContent()))
        .pageable(new PageInfo(page.getNumber(), page.getSize()))
        .totalPages(page.getTotalPages())
        .totalElements(page.getTotalElements())
        .build();
  }

//  public abstract ProductSearchPagedResponse toPagedResponse(Page<Product> page);

}
