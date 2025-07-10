package com.sparta.project1.domain.product.service.admin;

import com.sparta.project1.common.exception.ServiceException;
import com.sparta.project1.common.exception.ServiceExceptionCode;
import com.sparta.project1.domain.category.entity.Category;
import com.sparta.project1.domain.category.repository.CategoryRepository;
import com.sparta.project1.domain.product.dto.admin.ProductAdminRequest;
import com.sparta.project1.domain.product.dto.admin.ProductAdminResponse;
import com.sparta.project1.domain.product.entity.Product;
import com.sparta.project1.domain.product.mapper.ProductAdminMapper;
import com.sparta.project1.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductAdminService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductAdminMapper productAdminMapper;

    //상품등록
    @Transactional
    public ProductAdminResponse productAdminRegister(ProductAdminRequest request) {
        //상품명 중복검사 : Optional 타입에서는 존재유무를 isEmpty()보다 isPresent() 사용권장
        if(productRepository.findByName(request.getName()).isPresent()) {
            throw new ServiceException(ServiceExceptionCode.DUPLICATE_NAME);
        }

        //카테고리 조회
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_CATEGORY));

        Product product = productRepository.save(Product.builder()
                        .name(request.getName())
                        .description(request.getDescription())
                        .price(request.getPrice())
                        .stock(request.getStock())
                        .category(category)
                        .build());
        
        return productAdminMapper.toResponse(product);
    }

    //상품수정
    @Transactional
    public ProductAdminResponse productAdminUpdate(Long productId, ProductAdminRequest request) {
        //변경대상 ID 존재하는지 체크
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

        //setter 처리로 별도 save 없이 변경감지로인한 update 수행
        product.update(request.getName(), request.getDescription(), request.getPrice(), request.getStock());

        return productAdminMapper.toResponse(product);
    }
    
    //상품삭제
    @Transactional
    public void productAdminDelete(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));
        
        //삭제대상이 주문완료에 포함되어있는지 확인 후 있으면 삭제거부에러반환
        
        productRepository.delete(product);
    }
}
