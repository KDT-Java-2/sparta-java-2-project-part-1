package com.sparta.commerce.domain.product.repository;

import com.sparta.commerce.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // 추가적인 메소드 정의가 필요할 경우 여기에 작성
    Boolean existsByName(String name);

    Boolean existsByCategoryId(Long categoryId);

}
