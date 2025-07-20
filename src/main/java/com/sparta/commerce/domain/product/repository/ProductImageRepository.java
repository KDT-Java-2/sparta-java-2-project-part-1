package com.sparta.commerce.domain.product.repository;

import com.sparta.commerce.domain.product.entity.Product;
import com.sparta.commerce.domain.product.entity.ProductImage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
  ProductImage findByProductAndIsThumbnailTrue(Product product);
  List<ProductImage> findByProductInAndIsThumbnailTrue(List<Product> products);
  List<ProductImage> findByProduct(Product product);
  void deleteByProduct(Product product);
}
