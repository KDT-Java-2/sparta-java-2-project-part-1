package me.chahyunho.projectweek1.domain.category.repository;

import me.chahyunho.projectweek1.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
