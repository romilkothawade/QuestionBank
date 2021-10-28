package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SortProductService {
    public List<Product> SortProductList(List<Product> inputList) {
        List<Product> sortedList = inputList.stream().sorted(Comparator.comparing(Product::getProductId).thenComparing(Product::getLaunchDate)).collect(Collectors.toList());
        return sortedList;
    }
}
