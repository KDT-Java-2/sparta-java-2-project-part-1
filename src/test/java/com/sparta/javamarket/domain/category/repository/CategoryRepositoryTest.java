package com.sparta.javamarket.domain.category.repository;

import com.sparta.javamarket.domain.category.entity.Category;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

//@Transactional
@SpringBootTest
public class CategoryRepositoryTest {
//
//  @Autowired
//  private CategoryRepository categoryRepository;
//
//  @Test
//  void 최상위카테고리등록(){
//
//    Category category = categoryRepository.save(Category.builder()
//        .name("전자기기")
//        .build());
//  }
//
//  @Test
//  void 상위카테고리등록(){
//
//    Category parent = categoryRepository.findByName("전자기기");
//
//    Category category = categoryRepository.save(Category.builder()
//        .name("컴퓨터")
//        .parent(parent)
//        .build());
//  }
//
//  @Test
//  void 카테고리등록(){
//
//    Category parent = categoryRepository.findByName("컴퓨터");
//
//    Category category = categoryRepository.save(Category.builder()
//        .name("애플")
//        .parent(parent)
//        .build());
//  }
//
//  @Test
//  void 카테고리조회(){
//
//    List<Category> categorys = categoryRepository.findAll();
//
//    for (Category ca : categorys){
//      System.out.println("@@@ : " + ca.getName() + " / " + ca.getParent());
//    }
//  }
//
//  @Test
//  void 카테고리명변경(){
//
//    Category category = categoryRepository.findByName("식품");
//
//    System.out.println("@@ : " + category.getName());
//    category.setName("운동기기");
//
//    categoryRepository.save(category);
//
//    category = categoryRepository.findByName("운동기기");
//
//    System.out.println("@@ : " + category.getName());
//
//  }

}
