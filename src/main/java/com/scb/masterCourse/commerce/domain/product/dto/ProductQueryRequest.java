package com.scb.masterCourse.commerce.domain.product.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductQueryRequest {

    Long category;

    Integer minPrice;

    Integer maxPrice;

    Integer page;

    Integer size;

    String sortBy;


    public Pageable toPageable() {
        int page = this.page != null ? this.page : 0;
        int size = this.size != null ? this.size : 10;

        String sort = "createdAt";
        Sort.Direction direction = Direction.DESC;

        if (this.sortBy != null) {
            String[] sortByArr = this.sortBy.split(",");

            sort = sortByArr[0];
            direction = Sort.Direction.fromString(sortByArr[1]);
        }

        return PageRequest.of(page, size, Sort.by(direction, sort));
    }
}
