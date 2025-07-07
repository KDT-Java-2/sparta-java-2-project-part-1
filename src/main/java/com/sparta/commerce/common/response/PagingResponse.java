package com.sparta.commerce.common.response;

import com.sparta.commerce.domain.product.entity.Product;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PagingResponse<T> {
  List<T> items;
  int page;
  int size;
  long totalElements;
  int totalPages;
  boolean last;

  public static <T> PagingResponse<T> of(List<T> items, Page<?> page){
    return PagingResponse.<T>builder()
        .items(items)
        .page(page.getNumber())
        .size(page.getSize())
        .totalElements(page.getTotalElements())
        .totalPages(page.getTotalPages())
        .last(page.isLast())
        .build();
  }
}
