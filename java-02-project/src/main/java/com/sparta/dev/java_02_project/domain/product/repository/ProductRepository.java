package com.sparta.dev.java_02_project.domain.product.repository;

import com.sparta.dev.java_02_project.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
