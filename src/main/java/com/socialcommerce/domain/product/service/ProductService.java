package com.socialcommerce.domain.product.service;

import com.socialcommerce.common.exception.ServiceException;
import com.socialcommerce.common.exception.ServiceExceptionCode;
import com.socialcommerce.domain.product.dto.ProductResponse;
import com.socialcommerce.domain.product.entity.Product;
import com.socialcommerce.domain.product.mapper.ProductMapper;
import com.socialcommerce.domain.product.repository.ProductQueryRepository;
import com.socialcommerce.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductQueryRepository productQueryRepository;
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public Page<ProductResponse> searchProducts(
      Long category,
      Integer minPrice,
      Integer maxPrice,
      int page,
      int size,
      String sortBy) {
    Pageable pageable = createPageable(page, size, sortBy);
    // Product Entity -> .map -> ProductResponse 변환
    return productQueryRepository.searchProducts(category, minPrice, maxPrice, pageable)
        .map(productMapper::toResponse);
  }

  private Pageable createPageable(int page, int size, String sortBy) {
    String[] sort = sortBy.split(",");
    Direction direction = sort.length > 1 && sort[1].equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC;
    return PageRequest.of(page, size, Sort.by(direction, sort[0]));
  }

  public ProductResponse findProduct(Long productId){

    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    return ProductResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .stock(product.getStock())
        .categoryId(product.getCategory().getId())
        .build();
  }

}
