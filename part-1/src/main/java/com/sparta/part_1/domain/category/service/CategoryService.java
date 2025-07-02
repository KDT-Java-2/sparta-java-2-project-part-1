package com.sparta.part_1.domain.category.service;


import com.sparta.part_1.domain.category.dto.response.CategoryResponse;
import com.sparta.part_1.domain.category.repository.CategoryQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryQueryRepository repository;

  public List<CategoryResponse> getAllCategories() {
    return repository.findAll();
  }
}
