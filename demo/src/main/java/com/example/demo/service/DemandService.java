package com.example.demo.service;

import com.example.demo.model.Demand;
import com.example.demo.model.Supply;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemandService {
    private static List<Demand> demandList = new ArrayList<>();
    private static List<Supply> supplyList = new ArrayList<>();

    public void initializeDemand() {
        demandList.add(new Demand("Product1", 2.0));
        demandList.add(new Demand("Product2", 5.0));
    }

    public void initializeSupply() {
        supplyList.add(new Supply("Product1", 10.0));
        supplyList.add(new Supply("Product2", 5.0));
    }

    public double getAvailability(String productId) {
        double demandQuantity = demandList.stream().filter(i -> i.getProductId().equals(productId)).mapToDouble(i -> i.getQuantity()).sum();
        double supplyQuantity = supplyList.stream().filter(i -> i.getProductId().equals(productId)).mapToDouble(i -> i.getQuantity()).sum();
        return supplyQuantity-demandQuantity;
    }

}
