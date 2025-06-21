package com.scb.project.commerce.domain.product.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.scb.project.commerce.common.enums.ProductStatus;
import com.scb.project.commerce.domain.category.entity.Category;
import com.scb.project.commerce.domain.category.repository.CategoryRepository;
import com.scb.project.commerce.domain.product.entity.Product;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("기본 CRUD : 하나의 상품 조회에 성공합니다.")
    void selectOneProductSuccess() {
        Product product = productRepository.findById(2L)
            .orElseThrow(() -> new RuntimeException("존재하지 않는 상품입니다."));

        assertThat(product.getProductName()).isEqualTo("맥북");
    }

    @Test
    @DisplayName("기본 CRUD : 전체 상품 조회에 성공합니다.")
    void selectAllProductSuccess() {
        List<Product> products = productRepository.findAll();

        assertThat(products.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("기본 CRUD : 상품 생성에 성공합니다.")
    void saveProductSuccess() {
        // given
        Category category = categoryRepository.findByName("소설");

        Product product = Product.builder()
            .productName("무너지는 뇌를 끌어안고")
            .brandName("소미미디어")
            .description("소설 (무너지는 뇌를 끌어안고)")
            .price(BigDecimal.valueOf(15000))
            .stock(2)
            .status(ProductStatus.ON_SALE)
            .category(category)
            .build();

        // when
        productRepository.save(product);

        // then
        Product book = productRepository.findByProductName("무너지는 뇌를 끌어안고");

        assertThat(book.getProductName()).isEqualTo(product.getProductName());
    }

    @Test
    @DisplayName("기본 CRUD : 상품 수정에 성공합니다.")
    void updateProductSuccess() {
        // given
        Product product = productRepository.findByProductName("잔망루피 파자마");

        // when
        product.setProductName("짱구 파자마");

        productRepository.save(product);

        // then
        boolean isExists = productRepository.existsByProductName("잔망루피 파자마");
        assertThat(isExists).isFalse();
    }

    @Test
    @DisplayName("기본 CRUD : 하나의 상품 삭제에 성공합니다.")
    void deleteOneProductSuccess() {
        // given
        Product product = productRepository.findByProductName("잔망루피 파자마");

        // when
        productRepository.deleteById(product.getId());

        // then
        boolean isExists = productRepository.existsByProductName("잔망루피 파자마");
        assertThat(isExists).isFalse();
    }

    @Test
    @DisplayName("기본 CRUD : 특정 브랜드 상품 삭제에 성공합니다.")
    void deleteAllProductSuccess() {
        // given
        int productCount = productRepository.findAll().size();

        // FIXME: 쿼리가 'WHERE brandName = ?' 이 아니라 'WHERE id = ?'를 brandName 번 반복함 (수정필요)
        int appleProductCount = productRepository.findAllByBrandName("apple").size();

        // when
        // FIXME: 쿼리가 'WHERE brandName = ?' 이 아니라 'WHERE id = ?'를 brandName 번 반복함 (수정필요)
        productRepository.deleteAllByBrandName("apple");

        // then
        int allProductCount = productRepository.findAll().size();
        assertThat(allProductCount).isEqualTo(productCount - appleProductCount);
    }
}