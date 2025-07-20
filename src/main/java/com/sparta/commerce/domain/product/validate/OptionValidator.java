package com.sparta.commerce.domain.product.validate;

import static com.sparta.commerce.common.exception.ServiceExceptionCode.STOCK_CANNOT_BE_NEGATIVE;

import com.sparta.commerce.common.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptionValidator {

  public void validateStock(int stock) {
    if (stock < 0) {
      throw new ServiceException(STOCK_CANNOT_BE_NEGATIVE);
    }
  }

}
