package com.sparta.commerce.domain.product.repository;

import com.sparta.commerce.domain.product.entity.OptionGroup;
import com.sparta.commerce.domain.product.entity.OptionItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionItemRepository extends JpaRepository<OptionItem, Long> {
  List<OptionItem> findByOptionGroup(OptionGroup optionGroup);

}
