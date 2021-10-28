package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.SortProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductSortingController {

    @Autowired
    private SortProductService service;

    @PostMapping("/sortProduct")
    public List<Product> ProductListController(@RequestBody List<Product> inputList) {
        System.out.println("in controller");
        List<Product> sortedList = service.SortProductList(inputList);
        return sortedList;
    }
}
