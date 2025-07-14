package com.moveuk.ecommerce.application.category;

import com.moveuk.ecommerce.application.category.response.CategoryResult;
import com.moveuk.ecommerce.domain.category.Category;
import com.moveuk.ecommerce.domain.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryFacade {

    private final CategoryService categoryService;

    public List<CategoryResult.CategoryTreeDto> findAllCategory() {
        List<Category> categories = categoryService.findAllWithParent();

        Map<Long, CategoryResult.CategoryTreeDto> dtoMap = new HashMap<>();
        List<CategoryResult.CategoryTreeDto> roots = new ArrayList<>();

        for (Category category : categories) {
            dtoMap.put(category.getId(), CategoryResult.CategoryTreeDto.from(category));
        }

        for (Category category : categories) {
            CategoryResult.CategoryTreeDto dto = dtoMap.get(category.getId());

            if (category.getParent() == null) {
                roots.add(dto);
            } else {
                CategoryResult.CategoryTreeDto parentDto = dtoMap.get(category.getParent().getId());
                if (parentDto != null) {
                    parentDto.children().add(dto);
                }
            }
        }

        return roots;
    }
}
