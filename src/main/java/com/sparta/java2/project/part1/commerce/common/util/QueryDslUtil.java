package com.sparta.java2.project.part1.commerce.common.util;

import org.springframework.data.domain.Sort;

public class QueryDslUtil {
    public static Sort toSort(String sortString) {
        if (sortString == null || sortString.isBlank()) {
            return Sort.unsorted();
        }

        String[] parts = sortString.split(",");
        String fieldName = parts[0];
        String direction = parts.length > 1 ? parts[1].toLowerCase() : Sort.Direction.ASC.name().toLowerCase();

        Sort.Direction sortDirection =
                direction.equals(Sort.Direction.DESC.name().toLowerCase()) ?
                        Sort.Direction.DESC :
                        Sort.Direction.ASC;

        return Sort.by(sortDirection, fieldName);
    }
}
