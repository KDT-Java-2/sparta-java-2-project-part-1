package com.example.shoppingmall.domain.product;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.shoppingmall.domain.category.entity.Category;
import com.example.shoppingmall.domain.category.repository.CategoryRepository;
import com.example.shoppingmall.domain.product.entity.Product;
import com.example.shoppingmall.domain.product.entity.ProductImage;
import com.example.shoppingmall.domain.product.entity.ProductOptionGroup;
import com.example.shoppingmall.domain.product.entity.ProductOptionValue;
import com.example.shoppingmall.domain.product.entity.ProductVariant;
import com.example.shoppingmall.domain.product.entity.ProductVariantOption;
import com.example.shoppingmall.domain.product.repository.ProductRepository;

@SpringBootTest
@ActiveProfiles("test")
class ProductEntityTest {

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private CategoryRepository categoryRepository;

        @Test
        @DisplayName("상품 및 모든 연관 엔티티 저장 테스트 - 색상+사이즈")
        void createProductWithAllRelations() {
                Category category = categoryRepository.findByName("미분류");

                // 상품 생성
                Product product = Product.builder()
                                .name("스파르타 티셔츠")
                                .description("스파르타 코딩 클럽을 수강하고 있는 수강생 들이 입는 티셔츠")
                                .category(category)
                                .price(BigDecimal.valueOf(15000))
                                .build();

                // 옵션 그룹 및 옵션 값 (색상)
                ProductOptionGroup colorGroup = ProductOptionGroup.builder()
                                .product(product)
                                .name("색상")
                                .build();
                ProductOptionValue colorValue = ProductOptionValue.builder()
                                .optionGroup(colorGroup)
                                .value("블랙")
                                .build();
                colorGroup.getOptionValues().add(colorValue);
                product.getOptionGroups().add(colorGroup);

                // 옵션 그룹 및 옵션 값 (사이즈)
                ProductOptionGroup sizeGroup = ProductOptionGroup.builder()
                                .product(product)
                                .name("사이즈")
                                .build();
                ProductOptionValue sizeValue = ProductOptionValue.builder()
                                .optionGroup(sizeGroup)
                                .value("S")
                                .build();
                sizeGroup.getOptionValues().add(sizeValue);
                product.getOptionGroups().add(sizeGroup);

                // 상품 변형 및 옵션 (블랙/S)
                ProductVariant variant = ProductVariant.builder()
                                .product(product)
                                .price(BigDecimal.valueOf(15000))
                                .stock(20)
                                .build();
                ProductVariantOption colorVariantOption = ProductVariantOption.builder()
                                .productVariant(variant)
                                .optionValue(colorValue)
                                .build();
                ProductVariantOption sizeVariantOption = ProductVariantOption.builder()
                                .productVariant(variant)
                                .optionValue(sizeValue)
                                .build();
                variant.getVariantOptions().add(colorVariantOption);
                variant.getVariantOptions().add(sizeVariantOption);
                product.getVariants().add(variant);

                // 이미지
                ProductImage image = ProductImage.builder()
                                .product(product)
                                .url("http://example.com/image.jpg")
                                .type("THUMBNAIL")
                                .sortOrder(0)
                                .build();
                product.getImages().add(image);

                // QnA
                // ProductQna qna = ProductQna.builder()
                // .product(product)
                // .question("이 상품은 재입고 되나요?")
                // .answer("네, 곧 재입고 예정입니다.")
                // .build();
                // product.getQnas().add(qna);

                // 저장 (cascade로 모두 저장됨)
                productRepository.save(product);

                // 검증
                // Product saved = productRepository.findById(product.getId()).orElseThrow();
                // assertThat(saved.getOptionGroups()).hasSize(2);
                // assertThat(saved.getOptionGroups().get(0).getOptionValues()).hasSize(1);
                // assertThat(saved.getOptionGroups().get(1).getOptionValues()).hasSize(1);
                // assertThat(saved.getVariants()).hasSize(1);
                // assertThat(saved.getVariants().get(0).getVariantOptions()).hasSize(2);
                // assertThat(saved.getImages()).hasSize(1);
                // assertThat(saved.getQnas()).hasSize(1);
        }

        @Test
        @DisplayName("색상/사이즈 다중 옵션 조합 상품 저장 테스트")
        void createProductWithMultipleOptionCombinations() {
                Category category = categoryRepository.findByName("미분류");

                // 상품 생성
                Product product = Product.builder()
                                .name("스파르타 티셔츠")
                                .description("스파르타 코딩 클럽을 수강하고 있는 수강생 들이 입는 티셔츠")
                                .category(category)
                                .price(BigDecimal.valueOf(15000))
                                .build();

                // 색상 옵션 그룹 및 값
                ProductOptionGroup colorGroup = ProductOptionGroup.builder()
                                .product(product)
                                .name("색상")
                                .build();
                ProductOptionValue black = ProductOptionValue.builder().optionGroup(colorGroup).value("블랙").build();
                ProductOptionValue white = ProductOptionValue.builder().optionGroup(colorGroup).value("화이트").build();
                ProductOptionValue navy = ProductOptionValue.builder().optionGroup(colorGroup).value("네이비").build();
                colorGroup.getOptionValues().addAll(java.util.List.of(black, white, navy));
                product.getOptionGroups().add(colorGroup);

                // 사이즈 옵션 그룹 및 값
                ProductOptionGroup sizeGroup = ProductOptionGroup.builder()
                                .product(product)
                                .name("사이즈")
                                .build();
                ProductOptionValue s = ProductOptionValue.builder().optionGroup(sizeGroup).value("S").build();
                ProductOptionValue m = ProductOptionValue.builder().optionGroup(sizeGroup).value("M").build();
                ProductOptionValue l = ProductOptionValue.builder().optionGroup(sizeGroup).value("L").build();
                sizeGroup.getOptionValues().addAll(java.util.List.of(s, m, l));
                product.getOptionGroups().add(sizeGroup);

                // 모든 조합(variant) 생성 (색상 x 사이즈) - 가격/재고 다르게
                BigDecimal[][] prices = {
                    {BigDecimal.valueOf(13000), BigDecimal.valueOf(15000), BigDecimal.valueOf(17000)}, // S
                    {BigDecimal.valueOf(13500), BigDecimal.valueOf(15500), BigDecimal.valueOf(17500)}, // M
                    {BigDecimal.valueOf(14000), BigDecimal.valueOf(16000), BigDecimal.valueOf(18000)}  // L
                };
                int[][] stocks = {
                    {9, 8, 5}, // S
                    {27, 26, 24},  // M
                    {13, 12, 11}   // L
                };
                java.util.List<ProductOptionValue> colors = java.util.List.of(black, white, navy);
                java.util.List<ProductOptionValue> sizes = java.util.List.of(s, m, l);
                for (int i = 0; i < colors.size(); i++) {
                    for (int j = 0; j < sizes.size(); j++) {
                        ProductVariant variant = ProductVariant.builder()
                                .product(product)
                                .price(prices[j][i]) // 사이즈별로 행, 색상별로 열
                                .stock(stocks[j][i])
                                .build();
                        variant.getVariantOptions().add(ProductVariantOption.builder().productVariant(variant).optionValue(colors.get(i)).build());
                        variant.getVariantOptions().add(ProductVariantOption.builder().productVariant(variant).optionValue(sizes.get(j)).build());
                        product.getVariants().add(variant);
                    }
                }

                // 이미지
                ProductImage image = ProductImage.builder()
                                .product(product)
                                .url("http://example.com/image.jpg")
                                .type("THUMBNAIL")
                                .sortOrder(0)
                                .build();
                product.getImages().add(image);

                // 저장 (cascade로 모두 저장됨)
                productRepository.save(product);
        }
}