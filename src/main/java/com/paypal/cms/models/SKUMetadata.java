package com.paypal.cms.models;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class SKUMetadata implements Serializable {
    private Long id;
    private Long skuId;
    private String description;
    private String returnPolicy;
    private String warrantySummary;
    private Map<String, String> specifications;
}
