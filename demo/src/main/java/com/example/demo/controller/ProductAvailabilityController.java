package com.example.demo.controller;

import com.example.demo.model.Availability;
import com.example.demo.model.Capacity;
import com.example.demo.model.StoreInput;
import com.example.demo.model.StoreResponse;
import com.example.demo.service.ProductAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class ProductAvailabilityController {

    @Autowired
    private ProductAvailabilityService storeService;

    @PostMapping("/getProdAvailability")
    public ResponseEntity<StoreResponse> getProductAvailablity(@RequestBody StoreInput input) {
        storeService.setAvailability();
        storeService.setCapacity();
        CompletableFuture<Availability> availabilityCompletableFuture = storeService.getAvailability(input.getStoreNo());
        CompletableFuture<Capacity> capacityCompletableFuture = storeService.getCapacity(input.getStoreNo());
        CompletableFuture.allOf(availabilityCompletableFuture, capacityCompletableFuture);
        String status = storeService.getStatus(input);
        if (status != null) {
            StoreResponse storeResponse = new StoreResponse(input.getStoreNo(), input.getProductId(), input.getReqQty(), input.getReqDate(), status);
            return new ResponseEntity<StoreResponse>(storeResponse, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


