package me.chahyunho.projectweek1.domain.purchase.mapper;


import me.chahyunho.projectweek1.domain.purchase.dto.PurchaseResponse;
import me.chahyunho.projectweek1.domain.purchase.entity.Purchase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

  PurchaseResponse fromEntity(Purchase purchase);
}
