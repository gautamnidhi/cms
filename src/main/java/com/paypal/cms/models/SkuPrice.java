package com.paypal.cms.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class SkuPrice implements Serializable {
    private Long id;
    private String skuCode;
    private Double mrp;
    private Double sellingPrice;
    private Double discount;
}
