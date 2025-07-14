package com.moveuk.ecommerce.domain.category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAllWithParent();
}
