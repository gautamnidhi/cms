package com.paypal.cms.services;

import com.paypal.cms.models.SKUMetadata;
import com.paypal.cms.repositories.SkuMetaRepository;
import org.springframework.stereotype.Service;

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
        return skuMetaRepository.findBySkuId(skuId);
    }
}
