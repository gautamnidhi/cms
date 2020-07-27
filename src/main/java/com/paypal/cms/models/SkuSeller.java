package com.paypal.cms.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class SkuSeller implements Serializable {
    private Long id;
    private String skuCode;
}
