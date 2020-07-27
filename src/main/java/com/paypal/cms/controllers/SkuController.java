package com.paypal.cms.controllers;

import com.paypal.cms.models.SKU;
import com.paypal.cms.services.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @PutMapping("/update")
    public boolean update(@RequestParam(name = "skuId") Long skuId, @RequestBody SKU sku) throws Exception {
        if (skuId == 0) {
            throw new Exception("No sku id given in req");
        }
        if (Objects.isNull(sku)) {
            throw new Exception("Null sku object");
        }
        if (Objects.isNull(sku.getId())) {
            sku.setId(skuId);
        }
        return skuService.update(sku);
    }

}
