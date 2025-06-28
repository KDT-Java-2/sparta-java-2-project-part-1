package com.sparta.java2.project.part1.commerce.common.util;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.sparta.java2.project.part1.commerce.common.enums.QueryDslOrderBy;
import com.sparta.java2.project.part1.commerce.domain.product.entity.QProduct;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class QueryDslUtil {
    public static OrderSpecifier<?> setOrderBy(String sortBy, OrderSpecifier<?> order) {
        String[] parts = sortBy.split(",");
        String fieldName = parts[0];
        String direction = parts.length > 1 ? parts[1].toLowerCase() : QueryDslOrderBy.ASC.getValue();

        BeanWrapper wrapper = new BeanWrapperImpl(QProduct.product);
        Object expression = wrapper.getPropertyValue(fieldName);
        if (expression instanceof ComparableExpressionBase<?>) {
            order = direction.equals(QueryDslOrderBy.DESC.getValue()) ?
                    ((ComparableExpressionBase<?>) expression).desc() :
                    ((ComparableExpressionBase<?>) expression).asc();
        }
        return order;
    }
}
