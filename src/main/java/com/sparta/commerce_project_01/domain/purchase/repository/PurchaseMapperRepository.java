package com.sparta.commerce_project_01.domain.purchase.repository;

import com.sparta.commerce_project_01.domain.purchase.dto.PurchaseSearchCondition;
import com.sparta.commerce_project_01.domain.purchase.dto.PurchaseSearchResponse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseMapperRepository {

  List<PurchaseSearchResponse> findPurchaseWithPagination(PurchaseSearchCondition condition);

}





