package com.sparta.javamarket.domain.admin.service;

import com.sparta.javamarket.common.exception.ServiceException;
import com.sparta.javamarket.common.exception.ServiceExceptionCode;
import com.sparta.javamarket.domain.admin.dto.AdminCreateRequest;
import com.sparta.javamarket.domain.admin.dto.AdminCreateResponse;
import com.sparta.javamarket.domain.admin.mapper.AdminMapper;
import com.sparta.javamarket.domain.admin.repository.AdminRepository;
import com.sparta.javamarket.domain.product.dto.ProductSearchResponse;
import com.sparta.javamarket.domain.product.entity.Product;
import com.sparta.javamarket.domain.product.mapper.ProductMapper;
import com.sparta.javamarket.domain.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

  private final ProductRepository productRepository;
  private final AdminMapper adminMapper;
  private final ProductMapper productMapper;

  @Transactional
  public AdminCreateResponse adminCreateProduct(AdminCreateRequest adminCreateRequest) {

    Product createProduct = productRepository.save(adminMapper.toAdminCreateRequest(adminCreateRequest));

    return adminMapper.toAdminCreateResponse(createProduct);

  }

  @Transactional
  public ProductSearchResponse adminUpdateProduct(Long productId, AdminCreateRequest request) {
    Product product = getProduct(productId);

    product.setName(request.getName());
    product.setDescription(request.getDescription());
    product.setPrice(request.getPrice());
    product.setStock(request.getStock());
    product.setCategoryId(request.getCategoryId());

    return productMapper.toResponse(productRepository.save(product));

  }

  @Transactional
  public void adminDeleteProduct(Long productId) {
    Product product = getProduct(productId);
    productRepository.delete(product);
  }

  private Product getProduct(Long productId){
    return productRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));
  }

}
