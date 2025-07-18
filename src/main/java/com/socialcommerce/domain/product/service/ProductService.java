package com.socialcommerce.domain.product.service;

import com.socialcommerce.domain.product.dto.ProductResponse;
import com.socialcommerce.domain.product.mapper.ProductMapper;
import com.socialcommerce.domain.product.repository.ProductQueryRepository;
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
}
