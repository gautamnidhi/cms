package com.paypal.cms.controllers;

import com.paypal.cms.models.SKU;
import com.paypal.cms.services.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sku")
public class SkuController {
    private final SkuService skuService;

    @Autowired
    public SkuController(SkuService skuService) {
        this.skuService = skuService;
    }

    @PostMapping("/save")
    public SKU save(@RequestBody SKU sku) {
        return skuService.save(sku);
    }

    @GetMapping("/search")
    public List<SKU> search(@RequestParam(name = "keyword") String keyword) {
        return skuService.search(keyword);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam(name = "skuId") Long skuId) {
         skuService.delete(skuId);
    }

}
