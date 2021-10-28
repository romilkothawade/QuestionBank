package com.example.demo.controller;

import com.example.demo.model.DemandInput;
import com.example.demo.model.DemandResponse;
import com.example.demo.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculateAvailabilityController {

    @Autowired
    private DemandService service;

    @PostMapping("/getAvailability")
    public ResponseEntity<DemandResponse> getAvailability(@RequestBody DemandInput input) {
        System.out.println("in controller");
        service.initializeDemand();
        service.initializeSupply();
        double qunatity = service.getAvailability(input.getProductId());
        if (qunatity > 0) {
            DemandResponse response = new DemandResponse(input.getProductId(), qunatity);
            return new ResponseEntity<DemandResponse>(response, HttpStatus.OK);
        }
        return new ResponseEntity<DemandResponse>(HttpStatus.NO_CONTENT);
    }
}
