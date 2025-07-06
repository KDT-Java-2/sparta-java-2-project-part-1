package com.sparta.commerce_project_01.domain.product.service;

import com.sparta.commerce_project_01.common.annotation.LogExecutionTime;
import com.sparta.commerce_project_01.common.enums.exception.ServiceException;
import com.sparta.commerce_project_01.common.enums.exception.ServiceExceptionCode;
import com.sparta.commerce_project_01.domain.product.dto.ProductRequest;
import com.sparta.commerce_project_01.domain.product.dto.ProductResponse;
import com.sparta.commerce_project_01.domain.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public ProductResponse save(ProductRequest productRequest) {
    return null;
  }

  @LogExecutionTime // 이메서드에서만 실행시간 측정 AOP가 적용됨
  public ProductResponse getById(Long productId) {
    if (ObjectUtils.isEmpty(productId)) {
      // 예외 발생전에 필요한 조치 할 수 있는 영역
      throw new ServiceException(ServiceExceptionCode.PRODUCT_NOT_FOUND);
    }
    return null;
  }

  public Void delete(Long productId) {
    if (ObjectUtils.isEmpty(productId)) {
      // 예외 발생전에 필요한 조치 할 수 있는 영역
      throw new ServiceException(ServiceExceptionCode.PRODUCT_NOT_FOUND);
    }
    productRepository.deleteById(productId);

    // ??
    return null;
  }

  public ProductResponse update(Long productId, ProductRequest productRequest) {
    return null;
  }


  public List<ProductResponse> getAll() {
    return null;
  }
}
