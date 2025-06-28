package me.chahyunho.projectweek1.domain.product.mapper;


import me.chahyunho.projectweek1.domain.admin.dto.AdminProductRequest;
import me.chahyunho.projectweek1.domain.product.dto.ProductRequest;
import me.chahyunho.projectweek1.domain.product.dto.ProductResponse;
import me.chahyunho.projectweek1.domain.product.dto.ProductSearchResponse;
import me.chahyunho.projectweek1.domain.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductResponse toSearch(Product product);

  ProductSearchResponse toSearchResponse(Product product);

  Product toEntity(ProductRequest request);
  
  Product toEntity(AdminProductRequest request);
}
