package me.chahyunho.projectweek1.domain.product.repository;


import java.util.Optional;
import me.chahyunho.projectweek1.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  Boolean existsByName(String name);

  @Query("SELECT p FROM Product p JOIN FETCH p.category WHERE p.id = :id")
  Optional<Product> findWithCategoryById(@Param("id") Long id);
}
