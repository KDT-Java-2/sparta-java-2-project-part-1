package com.sparta.spartajava2projectpart1.domain.purchase.service;

import com.sparta.spartajava2projectpart1.common.exception.ServiceException;
import com.sparta.spartajava2projectpart1.common.exception.ServiceExceptionCode;
import com.sparta.spartajava2projectpart1.domain.purchase.entity.Purchase;
import com.sparta.spartajava2projectpart1.domain.purchase.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;


    public List<Purchase> getAllPurchases(Long userId) {
        if (ObjectUtils.isEmpty(userId)) {
            throw new ServiceException(ServiceExceptionCode.NOT_FOUND_USER);
        }
        return new ArrayList<>();
    }
}
