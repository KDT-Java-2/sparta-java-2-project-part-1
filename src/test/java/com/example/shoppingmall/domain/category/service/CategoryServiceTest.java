package com.example.shoppingmall.domain.category.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.shoppingmall.domain.category.dto.CategoryHierarchyResponse;
import com.example.shoppingmall.domain.category.entity.Category;
import com.example.shoppingmall.domain.category.repository.CategoryRepository;
import com.example.shoppingmall.domain.product.entity.Product;
import com.example.shoppingmall.domain.product.repository.ProductRepository;

@SpringBootTest
class CategoryServiceTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;

    @Test
    @DisplayName("상위 및 하위 카테고리 생성 테스트")
    void setCategory() {
        // 1. 최상위 카테고리
        Category clothes = categoryRepository.save(new Category("의류", null));
        Category groceries = categoryRepository.save(new Category("식료품", null));

        // 1-1. 의류 하위
        Category top = categoryRepository.save(new Category("상의", clothes));
        Category bottom = categoryRepository.save(new Category("하의", clothes));

        // 1-1-1. 상의 하위
        categoryRepository.save(new Category("아우터", top));
        categoryRepository.save(new Category("셔츠", top));

        // 1-1-2. 하의 하위
        categoryRepository.save(new Category("슬랙스", bottom));
        categoryRepository.save(new Category("청바지", bottom));

        // 2-1. 식료품 하위
        Category vegetable = categoryRepository.save(new Category("야채", groceries));
        Category meat = categoryRepository.save(new Category("육류", groceries));

        // 2-1-1. 야채 하위
        categoryRepository.save(new Category("쌈채소", vegetable));

        // 2-1-2. 육류 하위
        categoryRepository.save(new Category("닭고기", meat));
        categoryRepository.save(new Category("돼지고기", meat));
        categoryRepository.save(new Category("소고기", meat));
    }

    @Test
    @DisplayName("카테고리 삭제 시 상품이 미분류로 이동하는지 테스트")
    void deleteCategory_movesProductsToUncategorized() {
        // 미분류 카테고리 생성
        Category uncategorized = categoryRepository.save(Category.builder().name("미분류").build());
        // 삭제할 카테고리 생성
        Category category = categoryRepository.save(Category.builder().name("전자기기").build());
        // 상품 2개 생성
        Product p1 = Product.builder().name("상품1").description("desc1").category(category).build();
        Product p2 = Product.builder().name("상품2").description("desc2").category(category).build();
        productRepository.save(p1);
        productRepository.save(p2);

        // 카테고리 삭제 로직 직접 호출
        List<Product> products = productRepository.findByCategory(category);
        for (Product product : products) {
            product.setCategory(uncategorized);
        }
        categoryRepository.delete(category);

        // 상품이 미분류로 이동했는지 검증
        List<Product> uncategorizedProducts = productRepository.findByCategory(uncategorized);
        assertThat(uncategorizedProducts).hasSize(2);
        assertThat(uncategorizedProducts).extracting("name").containsExactlyInAnyOrder("상품1", "상품2");
    }

    @Test
    void 카테고리_계층_조회_테스트() {
        // when
        List<CategoryHierarchyResponse> result = categoryService.getCategoryHierarchy();

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("의류");
        assertThat(result.get(1).getName()).isEqualTo("식료품");

        // 의류 하위
        List<CategoryHierarchyResponse> clothesChildren = result.get(0).getChildren();
        assertThat(clothesChildren).hasSize(2);
        assertThat(clothesChildren.get(0).getName()).isEqualTo("상의");
        assertThat(clothesChildren.get(1).getName()).isEqualTo("하의");

        // 상의 하위
        List<CategoryHierarchyResponse> topChildren = clothesChildren.get(0).getChildren();
        assertThat(topChildren).hasSize(2);
        assertThat(topChildren.get(0).getName()).isEqualTo("아우터");
        assertThat(topChildren.get(1).getName()).isEqualTo("셔츠");

        // 하의 하위
        List<CategoryHierarchyResponse> bottomChildren = clothesChildren.get(1).getChildren();
        assertThat(bottomChildren).hasSize(2);
        assertThat(bottomChildren.get(0).getName()).isEqualTo("슬랙스");
        assertThat(bottomChildren.get(1).getName()).isEqualTo("청바지");

        // 식료품 하위
        List<CategoryHierarchyResponse> groceriesChildren = result.get(1).getChildren();
        assertThat(groceriesChildren).hasSize(2);
        assertThat(groceriesChildren.get(0).getName()).isEqualTo("야채");
        assertThat(groceriesChildren.get(1).getName()).isEqualTo("육류");

        // 육류 하위
        List<CategoryHierarchyResponse> meatChildren = groceriesChildren.get(1).getChildren();
        assertThat(meatChildren).hasSize(3);
        assertThat(meatChildren.get(0).getName()).isEqualTo("닭고기");
        assertThat(meatChildren.get(1).getName()).isEqualTo("돼지고기");
        assertThat(meatChildren.get(2).getName()).isEqualTo("소고기");
    }
}