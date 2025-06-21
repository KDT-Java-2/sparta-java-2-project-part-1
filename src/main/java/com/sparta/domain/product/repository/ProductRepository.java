package com.sparta.domain.product.repository;

import com.sparta.domain.product.entity.Product;
import com.sparta.domain.refund.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


}
