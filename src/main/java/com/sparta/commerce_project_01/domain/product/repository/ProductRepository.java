package com.sparta.commerce_project_01.domain.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.commerce_project_01.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByIdIn(List<Long> ids);
}
