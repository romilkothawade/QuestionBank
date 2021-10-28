package com.example.demo.controller;

import com.example.demo.model.SupplyRecordInput;
import com.example.demo.service.SupplyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupplyRecordController {

    @Autowired
    SupplyRecordService service;

    @PostMapping("/getOrderStats")
    public ResponseEntity<SupplyResponse> getRecord(@RequestBody SupplyRecordInput input){
    service.setOrderRecords();
    if(input.getStatus().equals("MIN_SALE")){
        service.getMiniumQuantityProduct();
    }else{
        service.getMaximumQuantityProduct();
    }
    }

}
