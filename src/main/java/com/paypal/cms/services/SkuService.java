package com.paypal.cms.services;

import com.paypal.cms.models.SKU;
import com.paypal.cms.models.SKUMetadata;
import com.paypal.cms.repositories.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SkuService {
    private final SkuRepository skuRepository;
    private final SkuMetadataService skuMetadataService;
    private final IndexService indexService;

    @Autowired
    public SkuService(SkuRepository skuRepo, SkuMetadataService skuMetadataService, IndexService indexService) {
        this.skuRepository = skuRepo;
        this.skuMetadataService = skuMetadataService;
        this.indexService = indexService;
    }

    public SKU save(SKU sku) {
        sku = skuRepository.save(sku);
        if (sku.getSkuMetaData() != null) {
            sku.getSkuMetaData().setSkuId(sku.getId());
            SKUMetadata skuMetadata = skuMetadataService.save(sku.getSkuMetaData());
            sku.setSkuMetaData(skuMetadata);
            String title = sku.getTitle();
            String description = skuMetadata.getDescription();
            indexService.index(Arrays.asList(title, description), sku.getId());
        }
        return skuRepository.save(sku);
    }

    public List<SKU> search(String keyword) {
        Set<Long> skuIds = indexService.findByKeyword(keyword);
        if (CollectionUtils.isEmpty(skuIds)) {
            return Collections.emptyList();
        }
        return skuIds
                .stream()
                .map(skuRepository::findById)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public void delete(Long skuId) {
        String title = skuRepository.findById(skuId).getTitle();
        String description = skuMetadataService.findBySKUId(skuId).getDescription();
        indexService.deleteIndexedData(Arrays.asList(title, description), skuId);
        skuMetadataService.delete(skuId);
        skuRepository.deleteSKU(skuId);

    }
}
