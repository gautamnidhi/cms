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

    public boolean update(SKU sku) {
        //to index the title and description
        String titleIndexed = skuRepository.findById(sku.getId()).getTitle();
        String descriptionIndexed = skuMetadataService.findBySKUId(sku.getId()).getDescription();
        String title = "";
        String description = "";
        if (sku.getTitle() != null && !sku.getTitle().equals(titleIndexed)) {
            title = sku.getTitle();
        }
        if (sku.getSkuMetaData() != null && sku.getSkuMetaData().getDescription() != null && !sku.getSkuMetaData().getDescription().equals(descriptionIndexed)) {
            description = sku.getSkuMetaData().getDescription();
        }
        if (description.isEmpty() && title.isEmpty()) {
            indexService.deleteIndexedData(Arrays.asList(titleIndexed, descriptionIndexed), sku.getId());
            indexService.index(Arrays.asList(title, description), sku.getId());
        } else if (description.isEmpty()) {
            indexService.deleteIndexedData(Arrays.asList(titleIndexed, description), sku.getId());
            indexService.index(Arrays.asList(title, description), sku.getId());


        } else if (title.isEmpty()) {
            indexService.deleteIndexedData(Arrays.asList(title, descriptionIndexed), sku.getId());
            indexService.index(Arrays.asList(title, description), sku.getId());
        }
        if (Objects.nonNull(sku.getSkuMetaData())) {
            sku.getSkuMetaData().setSkuId(sku.getId());
            skuMetadataService.update(sku.getSkuMetaData());
        }
        return skuRepository.update(sku);

    }
}
