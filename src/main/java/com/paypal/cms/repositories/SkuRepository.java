package com.paypal.cms.repositories;

import com.paypal.cms.models.SKU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SkuRepository {
    private Map<Long, SKU> skuMap;
    private static Long countTillNow = 0L;

    @Autowired
    public SkuRepository() {
        skuMap = new HashMap<>();
    }


    public SKU save(SKU sku) {
        if (sku.getId() == null) {
            countTillNow++;
            sku.setId(countTillNow);
        }
        skuMap.put(sku.getId(), sku);
        return sku;
    }

    public void deleteSKU(Long id) {
        skuMap.remove(id);
    }

    public SKU findById(Long id) {
        return skuMap.get(id);
    }
}
