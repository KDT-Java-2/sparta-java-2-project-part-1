package com.sparta.ecommerce;


import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class CategoryFlatDto {

  Long id;
  String name;
  Long parentId;
}

class CategoryTreeDto {

  Long id;
  String name;
  List<CategoryTreeDto> children;
}

public class JavaProgramming {

  public static void main(String[] args) {
    List<CategoryFlatDto> categoryFlatDtos = Arrays.asList(
        new CategoryFlatDto(1L, "", null),
        new CategoryFlatDto(2L, "A", 1L),
        new CategoryFlatDto(3L, "B", 1L)
    );

//    List<CategoryTreeDto> categoryTreeDtos = convertToTree(categoryFlatDtos);
//
//    for (CategoryFlatDto categoryTreeDto : categoryFlatDtos) {
//      System.out.println("Category ID: " + categoryTreeDto.id);
//      System.out.println("Category Name: " + categoryTreeDto.name);
//      if (categoryTreeDto.children != null) {
//        for (CategoryTreeDto child : categoryTreeDto.children) {
//          System.out.println("  Child ID: " + child.id);
//          System.out.println("  Child Name: " + child.name);
//        }
//      }
//    }
  }


}
