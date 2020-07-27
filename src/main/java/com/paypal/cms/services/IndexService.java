package com.paypal.cms.services;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IndexService {
    private Map<String, Set<Long>> indexData;
    private Set<String> stopWords;

    public IndexService() {
        this.indexData = new HashMap<>();
        this.stopWords = new HashSet<>();
    }

    public void index(List<String> inputs, Long skuId) {
        for (String sentence : inputs) {
            String[] keywords = sentence.split(" ");

            for (String keyword : keywords) {
                Set<Long> skuIdList;
                if (stopWords.contains(keyword)) {
                    continue;
                }
                if (indexData.containsKey(keyword)) {
                    skuIdList = indexData.get(keyword);
                    skuIdList.add(skuId);
                } else {
                    skuIdList = new HashSet<>();
                    skuIdList.add(skuId);
                    indexData.put(keyword, skuIdList);
                }
            }
        }
    }

    public Set<Long> findByKeyword(String keyword) {
        return indexData.get(keyword);
    }

    void deleteIndexedData(List<String> inputs, Long skuId) {
        for (String sentence : inputs) {
            String[] keywords = sentence.split(" ");

            for (String keyword : keywords) {
                Set<Long> skuIdList;
                if (stopWords.contains(keyword)) {
                    continue;
                }
                if (indexData.containsKey(keyword)) {
                    skuIdList = indexData.get(keyword);
                    skuIdList.remove(skuId);
                }
            }
        }
    }
}
