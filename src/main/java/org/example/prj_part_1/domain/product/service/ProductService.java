package org.example.prj_part_1.domain.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.prj_part_1.domain.product.dto.ProductSearchRequest;
import org.example.prj_part_1.domain.product.dto.ProductSearchResponse;
import org.example.prj_part_1.domain.product.repository.ProductQueryRepository;
import org.example.prj_part_1.domain.product.repository.ProductRepository;
import org.example.prj_part_1.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductQueryRepository productQueryRepository;

  public ProductSearchResponse searchProducts(ProductSearchRequest request){
    return productQueryRepository.search(request);
  }

}
