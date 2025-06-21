package com.scb.project.commerce.domain.category.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.scb.project.commerce.domain.category.entity.Category;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("기본 CRUD : 단일 카테고리 조회에 성공했습니다.")
    void selectOneCategorySuccess() {
        Category category = categoryRepository.findById(3L)
            .orElseThrow(() -> new RuntimeException("해당 카테고리는 존재하지 않습니다."));

        assertThat(category.getName()).isEqualTo("노트북");
    }

    @Test
    @DisplayName("기본 CRUD : 전자제품 하위의 카테고리 조회에 성공했습니다.")
    void selectAllCategorySuccess() {
        Category electronic = categoryRepository.findByName("전자제품");

        List<Category> electronics = categoryRepository.findAllByParent(electronic);

        assertThat(electronics.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("기본 CRUD : 카테고리 생성에 성공했습니다.")
    void saveCategorySuccess() {
        // given
        Category category = categoryRepository.findByName("장난감");

        Category dollCategory = Category.builder()
            .name("인형")
            .parent(category)
            .build();

        // when
        categoryRepository.save(dollCategory);

        // then
        Category doll = categoryRepository.findByName("인형");

        assertThat(doll.getParent()).isEqualTo(category);
    }

    @Test
    @DisplayName("기본 CRUD : 카테고리 수정에 성공했습니다.")
    void updateCategorySuccess() {
        // given
        Category laptop = categoryRepository.findByName("노트북");

        Category clothe = categoryRepository.findByName("의류");

        // when
        laptop.setParent(clothe);
        categoryRepository.save(laptop);

        // then
        Category category = categoryRepository.findByName("노트북");

        assertThat(category.getParent()).isEqualTo(clothe);
    }

    @Test
    @DisplayName("기본 CRUD : 카테고리 하나 삭제에 성공했습니다.")
    void deleteOneCategorySuccess() {
        // given
        Category laptop = categoryRepository.findByName("노트북");

        // when
        categoryRepository.delete(laptop);

        // then
        boolean isExists = categoryRepository.existsById(laptop.getId());

        assertThat(isExists).isFalse();
    }

    @Test
    @DisplayName("기본 CRUD : 부모 카테고리 안의 자식 카테고리 삭제에 성공했습니다.")
    void deleteAllCategorySuccess() {
        // given
        Category category = categoryRepository.findById(2L)
            .orElseThrow(() -> new RuntimeException("해당 카테고리는 존재하지 않습니다."));

        //when
        categoryRepository.deleteAllByParent(category);

        // then
        boolean isExists = categoryRepository.existsByParent(category);
        assertThat(isExists).isFalse();
    }
}