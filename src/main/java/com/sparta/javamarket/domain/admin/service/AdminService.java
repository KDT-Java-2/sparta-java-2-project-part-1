package com.sparta.javamarket.domain.admin.service;

import com.sparta.javamarket.domain.admin.dto.AdminCreateRequest;
import com.sparta.javamarket.domain.admin.dto.AdminCreateResponse;
import com.sparta.javamarket.domain.admin.mapper.AdminMapper;
import com.sparta.javamarket.domain.admin.repository.AdminRepository;
import com.sparta.javamarket.domain.product.entity.Product;
import com.sparta.javamarket.domain.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

  private final ProductRepository productRepository;
  private final AdminMapper adminMapper;

  @Transactional
  public AdminCreateResponse adminCreateProduct(AdminCreateRequest adminCreateRequest) {

    Product createProduct = productRepository.save(adminMapper.toAdminCreateRequest(adminCreateRequest));

    return adminMapper.toAdminCreateResponse(createProduct);

  }

}
