package com.sparta.commerce.domain.seller.validate;

import static com.sparta.commerce.common.exception.ServiceExceptionCode.NOT_FOUND_SELLER;

import com.sparta.commerce.common.exception.ServiceException;
import com.sparta.commerce.domain.seller.entity.Seller;
import com.sparta.commerce.domain.seller.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SellerValidator {
  private final SellerRepository sellerRepository;

  public Seller getSeller(Long sellerId){
    return sellerRepository.findById(sellerId)
        .orElseThrow(() -> new ServiceException(NOT_FOUND_SELLER));
  }

}
