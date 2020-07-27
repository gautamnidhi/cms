package com.paypal.cms.repositories;

import com.paypal.cms.models.SKUMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SkuMetaRepository {

    private Map<Long, SKUMetadata> skuMetadataMap;
    private static Long countTillNow = 0L;

    @Autowired
    public SkuMetaRepository() {
        skuMetadataMap = new HashMap<>();
    }

    public SKUMetadata save(SKUMetadata skuMetadata) {
        countTillNow++;
        skuMetadata.setId(countTillNow);
        skuMetadataMap.put(skuMetadata.getSkuId(), skuMetadata);
        return skuMetadata;
    }

    public void delete(Long skuId) {
        skuMetadataMap.remove(skuId);
    }

    public SKUMetadata findBySkuId(Long skuId) {
        return skuMetadataMap.get(skuId);
    }

    public boolean update(SKUMetadata skuMetadata) {
        SKUMetadata metaData = skuMetadataMap.get(skuMetadata.getSkuId());
        if (Objects.isNull(metaData)) {
            return false;
        }
        if (Objects.nonNull(skuMetadata.getDescription()) && !skuMetadata.getDescription().isEmpty() && !skuMetadata.getDescription().equals(metaData.getDescription())) {
            metaData.setDescription(skuMetadata.getDescription());
        }
        skuMetadataMap.put(skuMetadata.getSkuId(), metaData);
        return true;
    }



}
