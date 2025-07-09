package com.sparta.commerce.domain.category.repository;

import com.sparta.commerce.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // 추가적인 메소드 정의가 필요할 경우 여기에 작성

}
