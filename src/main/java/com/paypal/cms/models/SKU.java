package com.paypal.cms.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class SKU {
    private Long id;
    private String title;
    private String brandName;
    private String skuCode;
    private Category category;
    private SKUMetadata skuMetaData;
    private List<SkuSeller> sellers;
    private SkuPrice skuPrice;


    public SKU() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SKUMetadata getSkuMetaData() {
        return skuMetaData;
    }

    public void setSkuMetaData(SKUMetadata skuMetaData) {
        this.skuMetaData = skuMetaData;
    }

    public List<SkuSeller> getSellers() {
        return sellers;
    }

    public void setSellers(List<SkuSeller> sellers) {
        this.sellers = sellers;
    }

    public SkuPrice getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(SkuPrice skuPrice) {
        this.skuPrice = skuPrice;
    }
}
