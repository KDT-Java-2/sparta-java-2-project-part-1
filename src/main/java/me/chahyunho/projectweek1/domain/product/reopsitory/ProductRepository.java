package me.chahyunho.projectweek1.domain.product.reopsitory;

import me.chahyunho.projectweek1.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
