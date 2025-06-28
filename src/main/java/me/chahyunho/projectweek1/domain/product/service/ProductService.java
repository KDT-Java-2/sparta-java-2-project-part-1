package me.chahyunho.projectweek1.domain.product.service;


import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.chahyunho.projectweek1.common.enums.exception.ServiceException;
import me.chahyunho.projectweek1.common.enums.exception.ServiceExceptionCode;
import me.chahyunho.projectweek1.domain.product.dto.CategoryOrderCountDTO;
import me.chahyunho.projectweek1.domain.product.dto.CategoryProductDTO;
import me.chahyunho.projectweek1.domain.product.dto.ProductRequest;
import me.chahyunho.projectweek1.domain.product.dto.ProductResponse;
import me.chahyunho.projectweek1.domain.product.dto.ProductSearchResponse;
import me.chahyunho.projectweek1.domain.product.dto.ProductUpdateRequest;
import me.chahyunho.projectweek1.domain.product.entity.Product;
import me.chahyunho.projectweek1.domain.product.mapper.ProductMapper;
import me.chahyunho.projectweek1.domain.product.repository.CategoryProductQueryRepository;
import me.chahyunho.projectweek1.domain.product.repository.ProductRepository;
import me.chahyunho.projectweek1.domain.product.repository.ProductsQueryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductMapper productMapper;
  private final ProductRepository productRepository;
  private final ProductsQueryRepository productsQueryRepository;
  private final CategoryProductQueryRepository categoryProductQueryRepository;

  @Transactional
  public Page<ProductSearchResponse> searchProducts(Long categoryId, BigDecimal minPrice,
      BigDecimal maxPrice, Integer page, Integer size, String sortBy) {

    int defaultPage = (page == null || page < 0) ? 0 : page;
    int defaultSize = (size == null || size <= 0) ? 10 : size;

    Pageable pageable = PageRequest.of(defaultPage, defaultSize);

    return productsQueryRepository.findProducts(categoryId, minPrice, maxPrice, pageable, sortBy);
  }

  @Transactional
  public ProductResponse findById(Long productId) {

    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(
            ServiceExceptionCode.NOT_FOUNT_DATA));

    return productMapper.toSearch(product);
  }

  @Transactional
  public void create(ProductRequest request) {
    productRepository.save(productMapper.toEntity(request));
  }

  @Transactional
  public void update(Long productId, ProductUpdateRequest request) {
    Product product = productRepository.findById(productId).orElseThrow(() -> new ServiceException(
        ServiceExceptionCode.NOT_FOUNT_DATA));

    product.setName(request.getName());
    product.setDescription(request.getDescription());
    product.setPrice(request.getPrice());
    product.setStock(request.getStock());

    productRepository.save(product);
  }

  @Transactional
  public void delete(Long productId) {
    productRepository.deleteById(productId);
  }

  @Transactional
  public List<CategoryOrderCountDTO> findCategoryOrderCounts() {
    return productsQueryRepository.findCategoryOrderCounts();
  }

  @Transactional
  public List<CategoryProductDTO> findCategoryProducts(String categoryName) {
    return categoryProductQueryRepository.findCategoryProducts(categoryName);
  }

  @Transactional
  public List<ProductSearchResponse> searchProduct(String name, BigDecimal minPrice,
      BigDecimal maxPrice) {
    return productsQueryRepository.searchProducts(name, minPrice, maxPrice);
  }
}
