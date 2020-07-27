package com.paypal.cms.services;

import com.paypal.cms.models.SKUMetadata;
import com.paypal.cms.repositories.SkuMetaRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SkuMetadataService {
    private final SkuMetaRepository skuMetaRepository;

    public SkuMetadataService(SkuMetaRepository skuMetaRepository) {
        this.skuMetaRepository = skuMetaRepository;
    }

    public SKUMetadata save(SKUMetadata metadata) {
        return skuMetaRepository.save(metadata);
    }

    void delete(Long skuId) {
        skuMetaRepository.delete(skuId);
    }

    SKUMetadata findBySKUId(Long skuId) {
        SKUMetadata skuMetadata = skuMetaRepository.findBySkuId(skuId);
        if (skuMetadata == null) {
            return new SKUMetadata();
        }
        return skuMetadata;
    }

    boolean update(SKUMetadata skuMetadata){
        if(Objects.nonNull(skuMetadata)){
            return skuMetaRepository.update(skuMetadata);
        }
        return false;
    }
}
